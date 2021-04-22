package com.maolabs.etapas.moedaazul.external.secondary.adapters.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoedaAzulRepository extends JpaRepository<MoedaAzulEntity, Long> {
    boolean existsByClienteId(Long clienteId);
}
