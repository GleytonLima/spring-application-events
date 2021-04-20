package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulAtualizadaMessage;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulConfirmadaMessage;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaAzulUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoDefinirOperacaoMoedaAzulUseCaseImpl implements PedidoDefinirOperacaoMoedaAzulUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final MessagePublisherPort messagePublisherPort;

    public void definiridOperacaoMoedaAzul(PedidoMoedaAzulConfirmadaMessage pedidoMoedaAzulConfirmadaEvent) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoMoedaAzulConfirmadaEvent.getPedidoId());
        pedido.definirMoedaAzulOperacao(pedidoMoedaAzulConfirmadaEvent.getIdOperacao());
        pedidoRepositoryPort.atualizarMoedaAzulOperacaoId(pedido);
        messagePublisherPort.publishMessage(new PedidoMoedaAzulAtualizadaMessage(pedidoMoedaAzulConfirmadaEvent.getCorrelationId(), pedido.getId()));
    }
}
