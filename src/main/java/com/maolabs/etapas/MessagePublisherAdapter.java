package com.maolabs.etapas;

import com.maolabs.etapas.pedido.external.http.dto.output.OutputMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MessagePublisherAdapter implements MessagePublisherPort {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void publishMessage(IMessage event) {
        sendMessage(event);
        applicationEventPublisher.publishEvent(event);
    }

    public void sendMessage(IMessage o) {
        final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        simpMessagingTemplate.convertAndSend("/topic/messages",
                new OutputMessage(o.getClass().getSimpleName(), o.getCorrelationId().toString(), time));
    }
}
