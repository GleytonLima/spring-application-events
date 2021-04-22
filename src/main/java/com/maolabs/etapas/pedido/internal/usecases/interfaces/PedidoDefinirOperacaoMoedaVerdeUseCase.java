package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;

public interface PedidoDefinirOperacaoMoedaVerdeUseCase {
    void definiridOperacaoMoedaVerde(MoedaVerdeConsumidaEvent pedidoMoedaVerdeConfirmadaEvent);
}
