package com.maolabs.etapas.pedido.internal.usecases;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaVerdeConsumoRegistradoEvent;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import com.maolabs.etapas.pedido.internal.usecases.interfaces.PedidoDefinirOperacaoMoedaVerdeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Transactional
public class PedidoDefinirOperacaoMoedaVerdeUseCaseImpl implements PedidoDefinirOperacaoMoedaVerdeUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final MessagePublisherPort messagePublisherPort;

    @Override
    public void definiridOperacaoMoedaVerde(MoedaVerdeConsumidaEvent pedidoMoedaVerdeConfirmadaEvent) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Pedido pedido = pedidoRepositoryPort.buscarPorId(pedidoMoedaVerdeConfirmadaEvent.getPedidoId());
        pedido.definirMoedaVerdeOperacao(pedidoMoedaVerdeConfirmadaEvent.getIdOperacao());
        pedidoRepositoryPort.atualizarMoedaVerdeOperacaoId(pedido);
        messagePublisherPort.publishMessage(new PedidoMoedaVerdeConsumoRegistradoEvent(pedidoMoedaVerdeConfirmadaEvent.getCorrelationId(), pedido.getId()));
    }
}
