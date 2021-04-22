var stompClient = null;
var baseUrl = "";

function setConnected(connected) {
  document.getElementById("disconnect").disabled = !connected;
  document.getElementById("conversationDiv").style.visibility = connected
    ? "visible"
    : "hidden";
  document.getElementById("response").innerHTML = "";
}

function connect() {
  var socket = new SockJS(baseUrl + "/pedido/websocket");
  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log("Connected: " + frame);
    stompClient.subscribe(
      baseUrl + "/pedido/eventos",
      function (messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
      }
    );
  });
}

function disconnect() {
  if (stompClient != null) {
    stompClient.disconnect();
  }

  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  var clienteId = document.getElementById("clienteId").value;
  var moedasAzuis = document.getElementById("moedasAzuis").value;
  var moedasVerdes = document.getElementById("moedasVerdes").value;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", baseUrl + "/pedidos", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send(
    JSON.stringify({
      clienteId,
      moedasAzuis,
      moedasVerdes,
    })
  );
}

function showMessageOutputOld(messageOutput) {
  var response = document.getElementById("response");
  var p = document.createElement("p");
  p.style.wordWrap = "break-word";
  p.appendChild(
    document.createTextNode(
      " (" +
        messageOutput.time +
        ")" +
        messageOutput.from +
        ": " +
        messageOutput.text
    )
  );
  response.appendChild(p);
}

function showMessageOutput(messageOutput) {
  var tbody = $("#myTable").children("tbody");
  var table = tbody.length ? tbody : $("#myTable");
  table.append(
    "<tr>" +
      "<td>" +
      messageOutput.time +
      "</td>" +
      "<td>" +
      messageOutput.from +
      "</td>" +
      "<td>" +
      messageOutput.text +
      "</td>" +
      "</tr>"
  );
}
connect();
