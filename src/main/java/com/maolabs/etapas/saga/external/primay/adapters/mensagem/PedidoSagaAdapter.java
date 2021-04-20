package com.maolabs.etapas.saga.external.primay.adapters.mensagem;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCanceladoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCriadoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulConfirmadaMessage;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaVerdeConfirmadaMessage;
import com.maolabs.etapas.saga.external.primay.ports.PedidoSagaPort;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoSagaAdapter implements PedidoSagaPort {

    MessagePublisherPort messagePublisherPort;

    @Override
    @EventListener
    public void handle(PedidoCriadoEvent pedidoCriadoEvent) {
        messagePublisherPort.publishMessage(new MoedaAzulConsumirCommand(
                pedidoCriadoEvent.getCorrelationId(),
                pedidoCriadoEvent.getPedido().getId(),
                pedidoCriadoEvent.getPedido().getClienteId(),
                pedidoCriadoEvent.getPedido().getMoedasVerdes()
        ));
        messagePublisherPort.publishMessage(new MoedaVerdeConsumirCommand(
                pedidoCriadoEvent.getCorrelationId(),
                pedidoCriadoEvent.getPedido().getId(),
                pedidoCriadoEvent.getPedido().getClienteId(),
                pedidoCriadoEvent.getPedido().getMoedasAzuis()
        ));
    }

    @Override
    @EventListener
    public void handle(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent) {
        messagePublisherPort.publishMessage(new PedidoMoedaAzulConfirmadaMessage(
                moedaAzulConsumidaEvent.getCorrelationId(),
                moedaAzulConsumidaEvent.getPedidoId(),
                moedaAzulConsumidaEvent.getIdOperacao()
        ));
    }

    @Override
    @EventListener
    public void handle(MoedaVerdeConsumidaEvent moedaVerdeConsumidaEvent) {
        messagePublisherPort.publishMessage(new PedidoMoedaVerdeConfirmadaMessage(
                moedaVerdeConsumidaEvent.getCorrelationId(),
                moedaVerdeConsumidaEvent.getPedidoId(),
                moedaVerdeConsumidaEvent.getIdOperacao()
        ));
    }

    @Override
    @EventListener
    public void handle(MoedaAzulFalhaConsumoEvent moedaAzulFalhaConsumoEvent) {
        messagePublisherPort.publishMessage(new PedidoCancelarCommand(
                moedaAzulFalhaConsumoEvent.getCorrelationId(),
                moedaAzulFalhaConsumoEvent.getPedidoId(),
                moedaAzulFalhaConsumoEvent.getException().getMessage()
        ));
    }

    @Override
    @EventListener
    public void handle(MoedaVerdeFalhaConsumoEvent moedaVerdeFalhaConsumoEvent) {
        messagePublisherPort.publishMessage(new PedidoCancelarCommand(
                moedaVerdeFalhaConsumoEvent.getCorrelationId(),
                moedaVerdeFalhaConsumoEvent.getPedidoId(),
                moedaVerdeFalhaConsumoEvent.getException().getMessage()
        ));
    }

    @Override
    @EventListener
    public void handle(PedidoCanceladoEvent pedidoCanceladoEvent) {
        messagePublisherPort.publishMessage(new MoedaVerdeCompensarCommand(
                pedidoCanceladoEvent.getCorrelationId(),
                pedidoCanceladoEvent.getPedido().getId(),
                pedidoCanceladoEvent.getPedido().getClienteId(),
                pedidoCanceladoEvent.getPedido().getMoedasAzuis()
        ));

        messagePublisherPort.publishMessage(new MoedaAzulCompensarCommand(
                pedidoCanceladoEvent.getCorrelationId(),
                pedidoCanceladoEvent.getPedido().getId(),
                pedidoCanceladoEvent.getPedido().getClienteId(),
                pedidoCanceladoEvent.getPedido().getMoedasVerdes()
        ));
    }
}
