package com.maolabs.etapas.moedaverde.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MoedaVerdeConsumidaEvent implements IMessage {
    private UUID correlationId;
    private MoedaVerde moedaVerde;
    private Long pedidoId;
    private UUID idOperacao;
}
