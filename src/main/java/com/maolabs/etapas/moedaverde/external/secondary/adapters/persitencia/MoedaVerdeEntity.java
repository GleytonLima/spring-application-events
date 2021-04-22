package com.maolabs.etapas.moedaverde.external.secondary.adapters.persitencia;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerdeOperacaoTipo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MoedaVerdeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private Long pedidoId;
    private Long quantidade;
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    private MoedaVerdeOperacaoTipo operacaoTipo;

    public MoedaVerdeEntity(MoedaVerde moedaVerde) {
        this.id = moedaVerde.getId();
        this.clienteId = moedaVerde.getClienteId();
        this.pedidoId = moedaVerde.getPedidoId();
        this.quantidade = moedaVerde.getQuantidade();
        this.uuid = moedaVerde.getUuid();
        this.operacaoTipo = moedaVerde.getOperacaoTipo();
    }

    public MoedaVerde toModel() {
        var programaPontosB = new MoedaVerde(clienteId, pedidoId, quantidade, uuid, operacaoTipo);
        programaPontosB.definirID(this.id);
        return programaPontosB;
    }
}
