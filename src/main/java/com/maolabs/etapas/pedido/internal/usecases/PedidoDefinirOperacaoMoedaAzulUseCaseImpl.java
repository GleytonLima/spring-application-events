package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulConsumoRegistradoEvent;
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

    public void definiridOperacaoMoedaAzul(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent) {
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(moedaAzulConsumidaEvent.getPedidoId());
        pedido.definirMoedaAzulOperacao(moedaAzulConsumidaEvent.getIdOperacao());
        pedidoRepositoryPort.atualizarMoedaAzulOperacaoId(pedido);
        messagePublisherPort.publishMessage(new PedidoMoedaAzulConsumoRegistradoEvent(
                moedaAzulConsumidaEvent.getCorrelationId(),
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getMoedasVerdes()
        ));
    }
}
