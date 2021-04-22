package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCanceladoEvent;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoCancelarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoCancelarUseCaseImpl implements PedidoCancelarUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final MessagePublisherPort messagePublisherPort;

    @Override
    public void cancelar(PedidoCancelarCommand pedidoCancelarCommand) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoCancelarCommand.getPedidoId());
        pedido.cancelar();
        pedidoRepositoryPort.atualizarStatus(pedido.getStatus(), pedidoCancelarCommand.getPedidoId(), pedidoCancelarCommand.getMotivo());
        messagePublisherPort.publishMessage(new PedidoCanceladoEvent(pedidoCancelarCommand.getCorrelationId(), pedido));
    }
}
