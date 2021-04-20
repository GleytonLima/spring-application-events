package com.maolabs.etapas.moedaverde.internal.application;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.exceptions.MoedaVerdeInsuficienteException;
import lombok.Getter;

@Getter
public class MoedaVerde {
    private Long id;
    private Long clienteId;
    private Long saldo;

    public MoedaVerde(Long clienteId, Long saldo) {
        this.clienteId = clienteId;
        this.saldo = saldo;
    }

    public void adicionarPontos(Long pontos) {
        saldo = saldo + pontos;
    }

    public void consumirPontos(Long pontos) throws MoedaVerdeInsuficienteException {
        if (saldo - pontos < 0) {
            throw new MoedaVerdeInsuficienteException("Saldo de pontos B insuficiente");
        }
        saldo = saldo - pontos;
    }

    public void definirID(Long id) {
        assert id == null;
        this.id = id;
    }
}
