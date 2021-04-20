package com.maolabs.etapas.pedido.internal.application.mensagens.commands;

import com.maolabs.etapas.IMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PedidoCancelarCommand implements IMessage {
    private UUID correlationId;
    private Long pedidoId;
    private String motivo;
}
