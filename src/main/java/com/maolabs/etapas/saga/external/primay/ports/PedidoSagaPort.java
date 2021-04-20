package com.maolabs.etapas.saga.external.primay.ports;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCanceladoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCriadoEvent;
import org.springframework.context.event.EventListener;

public interface PedidoSagaPort {
    void handle(PedidoCriadoEvent pedidoCriadoEvent);
    void handle(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent);
    void handle(MoedaVerdeConsumidaEvent moedaVerdeConsumidaEvent);
    void handle(MoedaAzulFalhaConsumoEvent moedaAzulFalhaConsumoEvent);
    void handle(MoedaVerdeFalhaConsumoEvent moedaVerdeFalhaConsumoEvent);

    @EventListener
    void handle(PedidoCanceladoEvent pedidoCanceladoEvent);
}
