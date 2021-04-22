package com.maolabs.etapas.saga.ports;

import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoCanceladoEvent;

public interface PedidoSagaPort {
    void handle(PedidoCanceladoEvent pedidoCanceladoEvent);
}
