package com.maolabs.etapas.moedaazul.external.secondary.adapters.persistencia;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class MoedaAzulEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private Long numeroPontos;

    public MoedaAzulEntity(MoedaAzul moedaAzul) {
        this.id = moedaAzul.getId();
        this.clienteId = moedaAzul.getClienteId();
        this.numeroPontos = moedaAzul.getSaldo();
    }

    public MoedaAzul toModel() {
        var programaPontosA = new MoedaAzul(clienteId, numeroPontos);
        programaPontosA.definirID(this.id);
        return programaPontosA;
    }
}
