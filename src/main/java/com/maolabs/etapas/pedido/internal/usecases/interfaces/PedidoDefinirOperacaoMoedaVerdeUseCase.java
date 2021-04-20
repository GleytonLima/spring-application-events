package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaVerdeConfirmadaMessage;

public interface PedidoDefinirOperacaoMoedaVerdeUseCase {
    void definiridOperacaoMoedaVerde(PedidoMoedaVerdeConfirmadaMessage pedidoMoedaVerdeConfirmadaEvent);
}
