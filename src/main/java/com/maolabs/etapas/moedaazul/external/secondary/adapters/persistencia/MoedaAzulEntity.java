package com.maolabs.etapas.moedaazul.external.secondary.adapters.persistencia;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzulOperacaoTipo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MoedaAzulEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private Long pedidoId;
    private Long quantidade;
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    private MoedaAzulOperacaoTipo operacaoTipo;

    public MoedaAzulEntity(MoedaAzul moedaAzul) {
        this.id = moedaAzul.getId();
        this.clienteId = moedaAzul.getClienteId();
        this.pedidoId = moedaAzul.getPedidoId();
        this.quantidade = moedaAzul.getQuantidade();
        this.uuid = moedaAzul.getUuid();
        this.operacaoTipo = moedaAzul.getOperacaoTipo();
    }

    public MoedaAzul toModel() {
        var programaPontosA = new MoedaAzul(clienteId, pedidoId, quantidade, uuid, operacaoTipo);
        programaPontosA.definirID(this.id);
        return programaPontosA;
    }
}
