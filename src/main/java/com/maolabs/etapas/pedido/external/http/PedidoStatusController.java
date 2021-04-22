package com.maolabs.etapas.pedido.external.http;

import com.maolabs.etapas.pedido.external.http.dto.input.Message;
import com.maolabs.etapas.pedido.external.http.dto.output.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PedidoStatusController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @MessageMapping("/message-mapping")
    @SendTo("/pedido/eventos")
    public OutputMessage send(Message message) {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
