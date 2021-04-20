package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.Pedido;

import java.util.UUID;

public interface CriarPedidoUseCase {
    void criarPedido(Pedido pedido, UUID correlationId);
}
