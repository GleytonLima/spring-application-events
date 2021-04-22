package com.maolabs.etapas.moedaverde.internal.application;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MoedaVerde {
    private Long id;
    private Long clienteId;
    private Long pedidoId;
    private Long quantidade;
    private UUID uuid;
    private MoedaVerdeOperacaoTipo operacaoTipo;

    public MoedaVerde(Long clienteId, Long pedidoId, Long quantidade, UUID uuid, MoedaVerdeOperacaoTipo operacaoTipo) {
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
