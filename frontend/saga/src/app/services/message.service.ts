import { EventEmitter, Injectable } from "@angular/core";

import { environment } from "../../environments/environment";

declare var SockJS;
declare var Stomp;

@Injectable({
    providedIn: "root",
})
export class MessageService {
    topic: string = "/pedido/eventos";
    stompClient: any;
    public mensagemRecebidaEventEmitter: EventEmitter<string> = new EventEmitter();

    constructor() {}
    connect() {
        console.log("Iniciando conexão com WebSocket");
        let ws = new SockJS(environment.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        this.stompClient.connect(
            {},
            (frame) => {
                this.stompClient.subscribe(this.topic, function (sdkEvent) {
                    this.onMessageReceived(sdkEvent);
                });
                this.stompClient.reconnect_delay = 2000;
            },
            this.errorCallBack
        );
    }

    disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    errorCallBack(error) {
        console.log("errorCallBack -> " + error);
    }

    send(message) {
        this.stompClient.send(environment.messageEndpoint, {}, JSON.stringify(message));
    }

    onMessageReceived(message) {
        this.mensagemRecebidaEventEmitter.emit(message.body);
    }
}
