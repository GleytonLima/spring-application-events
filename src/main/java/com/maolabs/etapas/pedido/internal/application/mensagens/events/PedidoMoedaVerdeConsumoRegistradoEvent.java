package com.maolabs.etapas.pedido.internal.application.mensagens.events;

import com.maolabs.etapas.IMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PedidoMoedaVerdeConsumoRegistradoEvent implements IMessage {
    private UUID correlationId;
    private Long pedidoId;
}
