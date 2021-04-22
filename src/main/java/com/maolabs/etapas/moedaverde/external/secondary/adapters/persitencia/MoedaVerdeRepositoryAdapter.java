package com.maolabs.etapas.moedaverde.external.secondary.adapters.persitencia;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerdeOperacaoTipo;
import com.maolabs.etapas.moedaverde.internal.secondary.ports.MoedaVerdeRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MoedaVerdeRepositoryAdapter implements MoedaVerdeRepositoryPort {
    MoedaVerdeRepository programaARepository;

    @Override
    public void existsByClienteId(Long clienteId, Long pedidoId) {
        var existsByClienteId = programaARepository.existsByClienteId(clienteId);
        if (!existsByClienteId) {
            var creditoInicial = new MoedaVerde(clienteId, pedidoId, 100L, UUID.randomUUID(), MoedaVerdeOperacaoTipo.CREDITO);
            var moedaVerdeEntity = new MoedaVerdeEntity(creditoInicial);
            programaARepository.save(moedaVerdeEntity);
        }
    }

    @Override
    public void cadastrar(MoedaVerde programaPontosA) {
        programaARepository.save(new MoedaVerdeEntity(programaPontosA));
    }
}
