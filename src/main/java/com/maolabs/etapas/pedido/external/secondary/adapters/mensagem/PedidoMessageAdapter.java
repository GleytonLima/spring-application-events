package com.maolabs.etapas.pedido.external.secondary.adapters.mensagem;

import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.*;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoMessageAdapterPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoCancelarUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaAzulUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaVerdeUseCase;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoFinalizarUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PedidoMessageAdapter implements PedidoMessageAdapterPort {
    ApplicationEventPublisher applicationEventPublisher;
    PedidoDefinirOperacaoMoedaVerdeUseCase pedidoDefinirOperacaoMoedaVerdeUseCase;
    PedidoDefinirOperacaoMoedaAzulUseCase pedidoDefinirOperacaoMoedaAzulUseCase;
    PedidoFinalizarUseCase pedidoFinalizarUseCase;
    PedidoCancelarUseCase pedidoCancelarUseCase;

    @Override
    public void publicarEventoPedidoCriado(PedidoCriadoEvent pedidoCriadoEvent) {
        applicationEventPublisher.publishEvent(pedidoCriadoEvent);
    }

    @Override
    public void publicarEventoFalhaPersitir(PedidoFalhaPersitirEvent pedidoFalhaPersitirEvent) {
        log.info("Pedido nao foi salvo {}", pedidoFalhaPersitirEvent);
    }

    @EventListener
    @Override
    public void consumirPedidoMoedaAzulConfirmadaEvent(PedidoMoedaAzulConfirmadaMessage pedidoMoedaAzulConfirmadaEvent) {
        pedidoDefinirOperacaoMoedaAzulUseCase.definiridOperacaoMoedaAzul(pedidoMoedaAzulConfirmadaEvent);
    }

    @Override
    @EventListener
    public void consumirPedidoMoedaVerdeConfirmadaEvent(PedidoMoedaVerdeConfirmadaMessage pedidoMoedaVerdeConfirmadaEvent) {
        pedidoDefinirOperacaoMoedaVerdeUseCase.definiridOperacaoMoedaVerde(pedidoMoedaVerdeConfirmadaEvent);
    }

    @Override
    @EventListener
    public void consumirPedidoMoedaAzulAtualizadaEvent(PedidoMoedaAzulAtualizadaMessage pedidoMoedaAzulAtualizadaEvent) {
        pedidoFinalizarUseCase.finalizar(pedidoMoedaAzulAtualizadaEvent);
    }

    @Override
    @EventListener
    public void consumirPedidoMoedaVerdeAtualizadaEvent(PedidoMoedaVerdeAtualizadaMessage pedidoMoedaVerdeAtualizadaEvent) {
        pedidoFinalizarUseCase.finalizar(pedidoMoedaVerdeAtualizadaEvent);
    }

    @Override
    @EventListener
    public void processarPedidoCancelarCommand(PedidoCancelarCommand pedidoCancelarCommand) {
        pedidoCancelarUseCase.cancelar(pedidoCancelarCommand);
    }
}
