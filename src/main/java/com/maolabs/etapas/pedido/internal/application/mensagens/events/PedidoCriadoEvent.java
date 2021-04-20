package com.maolabs.etapas.pedido.internal.application.mensagens.events;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class PedidoCriadoEvent {
    private UUID correlationId;
    private Pedido pedido;
}
