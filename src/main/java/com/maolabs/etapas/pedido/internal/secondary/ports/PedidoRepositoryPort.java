package com.maolabs.etapas.pedido.internal.secondary.ports;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.PedidoStatus;

public interface PedidoRepositoryPort {
    Pedido criarNovoPedido(Pedido pedido) throws PedidoFalhaAoPersistirException;

    void atualizarMoedaAzulOperacaoId(Pedido pedido);

    void atualizarMoedaVerdeOperacaoId(Pedido pedido);

    void atualizarStatus(PedidoStatus pedido, Long id);

    Pedido buscarPorId(Long id);
}
