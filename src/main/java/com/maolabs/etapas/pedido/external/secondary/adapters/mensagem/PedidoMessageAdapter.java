package com.maolabs.etapas.pedido.external.secondary.adapters.mensagem;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCriadoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoFalhaPersitirEvent;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoMessageAdapterPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoCancelarUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaAzulUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaVerdeUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoFinalizarUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PedidoMessageAdapter implements PedidoMessageAdapterPort {
    MessagePublisherPort messagePublisherPort;
    PedidoDefinirOperacaoMoedaVerdeUseCase pedidoDefinirOperacaoMoedaVerdeUseCase;
    PedidoDefinirOperacaoMoedaAzulUseCase pedidoDefinirOperacaoMoedaAzulUseCase;
    PedidoFinalizarUseCase pedidoFinalizarUseCase;
    PedidoCancelarUseCase pedidoCancelarUseCase;

    @Override
    public void publicarEventoPedidoCriado(PedidoCriadoEvent pedidoCriadoEvent) {
        messagePublisherPort.publishMessage(pedidoCriadoEvent);
    }

    @Override
    public void publicarEventoFalhaPersitir(PedidoFalhaPersitirEvent pedidoFalhaPersitirEvent) {
        log.info("Pedido nao foi salvo {}", pedidoFalhaPersitirEvent);
    }

    @EventListener
    @Override
    public void consumirPedidoMoedaAzulConfirmadaEvent(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent) {
        pedidoDefinirOperacaoMoedaAzulUseCase.definiridOperacaoMoedaAzul(moedaAzulConsumidaEvent);
    }

    @Override
    @EventListener
    public void consumirPedidoMoedaVerdeConfirmadaEvent(MoedaVerdeConsumidaEvent moedaVerdeConsumidaEvent) {
        pedidoDefinirOperacaoMoedaVerdeUseCase.definiridOperacaoMoedaVerde(moedaVerdeConsumidaEvent);
        pedidoFinalizarUseCase.finalizar(moedaVerdeConsumidaEvent.getPedidoId(), moedaVerdeConsumidaEvent.getCorrelationId());
    }

    @Override
    @EventListener
    public void handle(MoedaAzulFalhaConsumoEvent moedaAzulFalhaConsumoEvent) {
        var pedidoCancelarCommand = new PedidoCancelarCommand(
                moedaAzulFalhaConsumoEvent.getCorrelationId(),
                moedaAzulFalhaConsumoEvent.getPedidoId(),
                moedaAzulFalhaConsumoEvent.getException().getMessage()
        );
        pedidoCancelarUseCase.cancelar(pedidoCancelarCommand);
    }

    @Override
    @EventListener
    public void handle(MoedaVerdeFalhaConsumoEvent moedaVerdeFalhaConsumoEvent) {
        var pedidoCancelarCommand = new PedidoCancelarCommand(
                moedaVerdeFalhaConsumoEvent.getCorrelationId(),
                moedaVerdeFalhaConsumoEvent.getPedidoId(),
                moedaVerdeFalhaConsumoEvent.getException().getMessage()
        );
        pedidoCancelarUseCase.cancelar(pedidoCancelarCommand);
    }
}
