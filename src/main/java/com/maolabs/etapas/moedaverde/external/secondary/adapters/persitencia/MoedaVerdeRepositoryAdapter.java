package com.maolabs.etapas.moedaverde.external.secondary.adapters.persitencia;

import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import com.maolabs.etapas.moedaverde.internal.secondary.ports.MoedaVerdeRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoedaVerdeRepositoryAdapter implements MoedaVerdeRepositoryPort {
    MoedaVerdeRepository programaARepository;

    @Override
    public MoedaVerde buscarPorClienteId(Long clienteId) {
        final MoedaVerdeEntity byClienteId = programaARepository.findByClienteId(clienteId);
        if (byClienteId == null) {
            var programaB = new MoedaVerde(clienteId, 100L);
            var programaBEntity = new MoedaVerdeEntity(programaB);
            final MoedaVerdeEntity save = programaARepository.save(programaBEntity);
            return save.toModel();
        }
        return byClienteId.toModel();
    }

    @Override
    public void atualizar(MoedaVerde programaPontosA) {
        programaARepository.save(new MoedaVerdeEntity(programaPontosA));
    }
}
