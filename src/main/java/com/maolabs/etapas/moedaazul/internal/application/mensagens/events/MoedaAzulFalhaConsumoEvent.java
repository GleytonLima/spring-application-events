package com.maolabs.etapas.moedaazul.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MoedaAzulFalhaConsumoEvent implements IMessage {
    private UUID correlationId;
    private Long pedidoId;
    private Exception exception;
}
