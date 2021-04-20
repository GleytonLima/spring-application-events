package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.pedido.internal.usecases.interfaces.CriarPedidoUseCase;
import com.maolabs.etapas.spring.config.Monitor;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCriadoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoFalhaPersitirEvent;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoFalhaAoPersistirException;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoMessageAdapterPort;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Monitor
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    PedidoRepositoryPort pedidoRepositoryPort;
    PedidoMessageAdapterPort pedidoMessageAdapterPort;

    @Override
    public void criarPedido(Pedido pedido, UUID correlationId) {
        try {
            Pedido pedidoSalvo = pedidoRepositoryPort.criarNovoPedido(pedido);
            pedidoMessageAdapterPort.publicarEventoPedidoCriado(new PedidoCriadoEvent(correlationId, pedidoSalvo));
        } catch (PedidoFalhaAoPersistirException e) {
            pedidoMessageAdapterPort.publicarEventoFalhaPersitir(new PedidoFalhaPersitirEvent(correlationId, pedido, e));
        }
    }
}
