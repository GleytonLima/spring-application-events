package com.maolabs.etapas.pedido.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import com.maolabs.etapas.pedido.internal.application.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PedidoFinalizadoMessage implements IMessage {
    private UUID correlationId;
    private Pedido pedido;
}
