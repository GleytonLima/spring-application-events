package com.maolabs.etapas.pedido.internal.application.mensagens.events;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@ToString
public class PedidoFalhaPersitirEvent {
    private UUID correlationId;
    private Pedido pedido;
    private Throwable erro;
}
