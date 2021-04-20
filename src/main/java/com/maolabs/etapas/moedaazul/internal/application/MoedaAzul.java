package com.maolabs.etapas.moedaazul.internal.application;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.exceptions.MoedaAzulInsuficienteException;
import lombok.Getter;

@Getter
public class MoedaAzul {
    private Long id;
    private Long clienteId;
    private Long saldo;

    public MoedaAzul(Long clienteId, Long saldo) {
        this.clienteId = clienteId;
        this.saldo = saldo;
    }

    public void adicionarPontos(Long pontos) {
        saldo = saldo + pontos;
    }

    public void consumirPontos(Long pontos) throws MoedaAzulInsuficienteException {
        if (saldo - pontos < 0) {
            throw new MoedaAzulInsuficienteException("Saldo moedas azuis insuficiente");
        }
        saldo = saldo - pontos;
    }

    public void definirID(Long id) {
        assert id == null;
        this.id = id;
    }
}
