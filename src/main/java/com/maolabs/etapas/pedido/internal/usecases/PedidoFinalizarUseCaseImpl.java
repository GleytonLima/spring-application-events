package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.PedidoStatus;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoFinalizadoMessage;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulAtualizadaMessage;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaVerdeAtualizadaMessage;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoFinalizarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoFinalizarUseCaseImpl implements PedidoFinalizarUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final MessagePublisherPort messagePublisherPort;

    @Override
    public void finalizar(PedidoMoedaAzulAtualizadaMessage pedidoMoedaAzulAtualizadaEvent) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoMoedaAzulAtualizadaEvent.getPedidoId());
        if (pedido.prontoParaFinalizacao()) {
            pedido.finalizarPedido();
            pedidoRepositoryPort.atualizarStatus(PedidoStatus.FINALIZADO, pedidoMoedaAzulAtualizadaEvent.getPedidoId());
            messagePublisherPort.publishMessage(new PedidoFinalizadoMessage(pedidoMoedaAzulAtualizadaEvent.getCorrelationId(), pedido));
        }
    }

    @Override
    public void finalizar(PedidoMoedaVerdeAtualizadaMessage pedidoMoedaVerdeAtualizadaEvent) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoMoedaVerdeAtualizadaEvent.getPedidoId());
        if (pedido.prontoParaFinalizacao()) {
            pedido.finalizarPedido();
            pedidoRepositoryPort.atualizarStatus(PedidoStatus.FINALIZADO, pedidoMoedaVerdeAtualizadaEvent.getPedidoId());
            messagePublisherPort.publishMessage(new PedidoFinalizadoMessage(pedidoMoedaVerdeAtualizadaEvent.getCorrelationId(), pedido));
        }
    }
}
