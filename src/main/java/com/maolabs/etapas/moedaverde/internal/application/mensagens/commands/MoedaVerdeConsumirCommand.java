package com.maolabs.etapas.moedaverde.internal.application.mensagens.commands;

import com.maolabs.etapas.IMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MoedaVerdeConsumirCommand implements IMessage {
    UUID correlationId;
    Long pedidoId;
    Long clienteId;
    Long totalPontos;
}
