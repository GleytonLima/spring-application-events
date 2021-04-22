package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;

public interface PedidoDefinirOperacaoMoedaAzulUseCase {
    void definiridOperacaoMoedaAzul(MoedaAzulConsumidaEvent moedaAzulConsumidaEvent);
}
