package com.maolabs.etapas.moedaverde.internal.secondary.ports;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;

public interface MoedaVerdeRepositoryPort {
    MoedaVerde buscarPorClienteId(Long clienteId);

    void atualizar(MoedaVerde programaPontosA);
}
