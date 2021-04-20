package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaAzulAtualizadaMessage;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.PedidoMoedaVerdeAtualizadaMessage;

public interface PedidoFinalizarUseCase {
    void finalizar(PedidoMoedaAzulAtualizadaMessage pedidoMoedaAzulAtualizadaEvent);
    void finalizar(PedidoMoedaVerdeAtualizadaMessage pedidoMoedaVerdeAtualizadaEvent);
}
