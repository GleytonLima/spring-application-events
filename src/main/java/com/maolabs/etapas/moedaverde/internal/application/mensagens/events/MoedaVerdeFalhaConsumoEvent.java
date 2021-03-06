package com.maolabs.etapas.moedaverde.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MoedaVerdeFalhaConsumoEvent implements IMessage {
    private UUID correlationId;
    private Long pedidoId;
    private Exception exception;
}
