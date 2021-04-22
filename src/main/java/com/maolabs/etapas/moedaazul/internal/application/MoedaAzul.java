package com.maolabs.etapas.moedaazul.internal.application;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MoedaAzul {
    private Long id;
    private Long clienteId;
    private Long pedidoId;
    private Long quantidade;
    private UUID uuid;
    private MoedaAzulOperacaoTipo operacaoTipo;

    public MoedaAzul(Long clienteId, Long pedidoId, Long quantidade, UUID uuid, MoedaAzulOperacaoTipo operacaoTipo) {
        this.clienteId = clienteId;
        this.pedidoId = pedidoId;
        this.quantidade = quantidade;
        this.uuid = uuid;
        this.operacaoTipo = operacaoTipo;
    }

    public void definirID(Long id) {
        assert id == null;
        this.id = id;
    }
}
