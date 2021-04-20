package com.maolabs.etapas.moedaazul.internal.secondary.ports;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;

public interface MoedaAzulRepositoryPort {
    MoedaAzul buscarPorClienteId(Long clienteId);

    void atualizar(MoedaAzul moedaAzul);
}
