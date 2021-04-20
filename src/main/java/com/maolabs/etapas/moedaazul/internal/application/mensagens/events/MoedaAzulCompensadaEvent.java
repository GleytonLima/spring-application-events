package com.maolabs.etapas.moedaazul.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MoedaAzulCompensadaEvent implements IMessage {
    private UUID correlationId;
    private MoedaAzul moedaAzul;
    private Long pedidoId;
    private UUID idOperacao;
}
