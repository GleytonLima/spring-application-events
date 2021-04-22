package com.maolabs.etapas.pedido.internal.application;

import com.maolabs.etapas.pedido.internal.application.exceptions.PedidoStatusInvalidoException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@ToString
@Builder
@Slf4j
public class Pedido {
    private Long id;
    private Long clienteId;
    private Long moedasAzuis;
    private Long moedasVerdes;
    private UUID moedaAzulOperacaoId;
    private UUID moedaVerdeOperacaoId;
    private PedidoStatus status;
    private String observacao;

    public void definirID(Long id) {
        assert this.id == null;
        this.id = id;
    }

    public void definirStatus(PedidoStatus status) {
        if (status.equals(PedidoStatus.FINALIZADO) && this.moedaAzulOperacaoId != null && this.moedaVerdeOperacaoId != null) {
            throw new PedidoStatusInvalidoException("Não é possível finalizar o pedido sem as operacoes de moeda verde e azul");
        }
        this.status = status;
    }

    public void finalizarPedido() {
        log.info("Tentando finalizar pedido: {}", this);
        if (status.equals(PedidoStatus.FINALIZADO)) {
            throw new PedidoStatusInvalidoException("Pedido já finalizado");
        }
        this.status = PedidoStatus.FINALIZADO;
    }

    public void cancelar() {
        log.info("Tentando CANCELAR pedido: {}", this);
        if (status.equals(PedidoStatus.FINALIZADO)) {
            throw new PedidoStatusInvalidoException("Pedido já finalizado");
        }
        this.status = PedidoStatus.CANCELADO;
    }

    public boolean prontoParaFinalizacao() {
        return this.moedaAzulOperacaoId != null && this.moedaVerdeOperacaoId != null && !this.status.equals(PedidoStatus.FINALIZADO);
    }

    public void definirMoedaAzulOperacao(UUID uuid) {
        assert this.moedaAzulOperacaoId == null;
        this.moedaAzulOperacaoId = uuid;
    }

    public void definirMoedaVerdeOperacao(UUID uuid) {
        assert this.moedaVerdeOperacaoId == null;
        this.moedaVerdeOperacaoId = uuid;
    }

    public boolean moedasAzuisConsumidas() {
        return getMoedaAzulOperacaoId() != null;
    }

    public boolean moedasVerdesConsumidas() {
        return getMoedaVerdeOperacaoId() != null;
    }
}
