package com.maolabs.etapas.pedido.internal.secondary.ports;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCriadoEvent;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoFalhaPersitirEvent;
import org.springframework.context.event.EventListener;

public interface PedidoMessageAdapterPort {

    void publicarEventoPedidoCriado(PedidoCriadoEvent pedidoCriadoEvent);

    void publicarEventoFalhaPersitir(PedidoFalhaPersitirEvent pedidoFalhaPersitirEvent);

    @EventListener
    void consumirPedidoMoedaAzulConfirmadaEvent(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent);

    @EventListener
    void consumirPedidoMoedaVerdeConfirmadaEvent(MoedaVerdeConsumidaEvent moedaVerdeConsumidaEvent);

    @EventListener
    void handle(MoedaAzulFalhaConsumoEvent moedaAzulFalhaConsumoEvent);

    @EventListener
    void handle(MoedaVerdeFalhaConsumoEvent moedaVerdeFalhaConsumoEvent);
}
