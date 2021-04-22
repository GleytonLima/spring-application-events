package com.maolabs.etapas.moedaazul.external.secondary.adapters.persistencia;

import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzulOperacaoTipo;
import com.maolabs.etapas.moedaazul.internal.secondary.ports.MoedaAzulRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MoedaAzulRepositoryAdapter implements MoedaAzulRepositoryPort {
    private MoedaAzulRepository moedaAzulRepository;

    @Override
    public void creditarSeNovoCliente(Long clienteId, Long pedidoId) {
        var existsByClienteId = moedaAzulRepository.existsByClienteId(clienteId);
        if (!existsByClienteId) {
            var programaA = new MoedaAzul(clienteId, pedidoId, 100L, UUID.randomUUID(), MoedaAzulOperacaoTipo.CREDITO);
            var programaAEntity = new MoedaAzulEntity(programaA);
            moedaAzulRepository.save(programaAEntity);
        }
    }

    @Override
    public void cadastrar(MoedaAzul moedaAzul) {
        moedaAzulRepository.save(new MoedaAzulEntity(moedaAzul));
    }
}
