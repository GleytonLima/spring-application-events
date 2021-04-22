package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import java.util.UUID;

public interface PedidoFinalizarUseCase {
    void finalizar(Long pedidoId, UUID correlationId);
}
