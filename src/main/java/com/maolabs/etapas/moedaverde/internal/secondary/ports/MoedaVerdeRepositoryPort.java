package com.maolabs.etapas.moedaverde.internal.secondary.ports;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;

public interface MoedaVerdeRepositoryPort {
    void existsByClienteId(Long clienteId, Long pedidoId);

    void cadastrar(MoedaVerde programaPontosA);
}
