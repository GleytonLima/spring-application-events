package com.maolabs.etapas.moedaazul.external.secondary.adapters.persistencia;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import com.maolabs.etapas.moedaazul.internal.secondary.ports.MoedaAzulRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoedaAzulRepositoryAdapter implements MoedaAzulRepositoryPort {
    private MoedaAzulRepository moedaAzulRepository;

    @Override
    public MoedaAzul buscarPorClienteId(Long clienteId) {
        final MoedaAzulEntity byClienteId = moedaAzulRepository.findByClienteId(clienteId);
        if (byClienteId == null) {
            var programaA = new MoedaAzul(clienteId, 100L);
            var programaAEntity = new MoedaAzulEntity(programaA);
            final MoedaAzulEntity save = moedaAzulRepository.save(programaAEntity);
            return save.toModel();
        }
        return byClienteId.toModel();
    }

    @Override
    public void atualizar(MoedaAzul moedaAzul) {
        moedaAzulRepository.save(new MoedaAzulEntity(moedaAzul));
    }
}
