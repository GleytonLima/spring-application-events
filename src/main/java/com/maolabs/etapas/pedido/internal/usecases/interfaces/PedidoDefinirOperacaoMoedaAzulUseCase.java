package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulConfirmadaMessage;

public interface PedidoDefinirOperacaoMoedaAzulUseCase {
    void definiridOperacaoMoedaAzul(PedidoMoedaAzulConfirmadaMessage pedidoMoedaAzulConfirmadaEvent);
}
