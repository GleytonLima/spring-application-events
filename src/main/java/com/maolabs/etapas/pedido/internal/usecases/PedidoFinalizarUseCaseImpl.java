package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.PedidoStatus;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoFinalizadoEvent;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoFinalizarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoFinalizarUseCaseImpl implements PedidoFinalizarUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final MessagePublisherPort messagePublisherPort;

    @Override
    public void finalizar(Long pedidoId, UUID correlationID) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoId);
        if (pedido.prontoParaFinalizacao()) {
            pedido.finalizarPedido();
            pedidoRepositoryPort.atualizarStatus(PedidoStatus.FINALIZADO, pedidoId, "");
            messagePublisherPort.publishMessage(new PedidoFinalizadoEvent(correlationID, pedido));
        }
    }
}
