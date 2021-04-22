package com.maolabs.etapas.moedaazul.internal.secondary.ports;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;

public interface MoedaAzulRepositoryPort {
    void creditarSeNovoCliente(Long clienteId, Long pedidoId);

    void cadastrar(MoedaAzul moedaAzul);
}
