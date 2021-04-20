package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.Pedido;

import java.util.UUID;

public interface FinalizarPedidoUseCase {
    void finalizar(Pedido pedido, UUID moedaAzulOperacaoId, UUID moedaVerdeOperacaoId);
}
