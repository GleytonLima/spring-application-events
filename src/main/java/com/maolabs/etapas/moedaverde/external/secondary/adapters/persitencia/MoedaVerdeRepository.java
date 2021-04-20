package com.maolabs.etapas.moedaverde.external.secondary.adapters.persitencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoedaVerdeRepository extends JpaRepository<MoedaVerdeEntity, Long> {
    MoedaVerdeEntity findByClienteId(Long clienteId);
}
