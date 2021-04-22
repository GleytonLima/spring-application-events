package com.maolabs.etapas.saga.adapters;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCanceladoEvent;
import com.maolabs.etapas.saga.ports.PedidoSagaPort;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoSagaAdapter implements PedidoSagaPort {

    MessagePublisherPort messagePublisherPort;

    @Override
    @EventListener
    public void handle(PedidoCanceladoEvent pedidoCanceladoEvent) {
        if (pedidoCanceladoEvent.getPedido().moedasVerdesConsumidas()) {
            messagePublisherPort.publishMessage(new MoedaVerdeCompensarCommand(
                    pedidoCanceladoEvent.getCorrelationId(),
                    pedidoCanceladoEvent.getPedido().getId(),
                    pedidoCanceladoEvent.getPedido().getClienteId(),
                    pedidoCanceladoEvent.getPedido().getMoedasVerdes()
            ));
        }

        if (pedidoCanceladoEvent.getPedido().moedasAzuisConsumidas()) {
            messagePublisherPort.publishMessage(new MoedaAzulCompensarCommand(
                    pedidoCanceladoEvent.getCorrelationId(),
                    pedidoCanceladoEvent.getPedido().getId(),
                    pedidoCanceladoEvent.getPedido().getClienteId(),
                    pedidoCanceladoEvent.getPedido().getMoedasAzuis()
            ));
        }
    }
}
