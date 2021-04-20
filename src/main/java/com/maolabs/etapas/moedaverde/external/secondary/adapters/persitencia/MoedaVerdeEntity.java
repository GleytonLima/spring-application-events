package com.maolabs.etapas.moedaverde.external.secondary.adapters.persitencia;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class MoedaVerdeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private Long numeroPontos;

    public MoedaVerdeEntity(MoedaVerde moedaVerde) {
        this.id = moedaVerde.getId();
        this.clienteId = moedaVerde.getClienteId();
        this.numeroPontos = moedaVerde.getSaldo();
    }

    public MoedaVerde toModel() {
        var programaPontosB = new MoedaVerde(clienteId, numeroPontos);
        programaPontosB.definirID(this.id);
        return programaPontosB;
    }
}
