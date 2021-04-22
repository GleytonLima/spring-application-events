package com.maolabs.etapas.pedido.external.secondary.adapters.persistencia;

import com.maolabs.etapas.pedido.internal.application.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Modifying
    @Query("update PedidoEntity p set p.moedaAzulOperacaoId = ?1 where p.id = ?2")
    int atualizarMoedaAzulOperacaoId(UUID moedaAzulOperacaoId, Long pedidoId);

    @Modifying
    @Query("update PedidoEntity p set p.moedaVerdeOperacaoId = ?1 where p.id = ?2")
    int atualizarMoedaVerdeOperacaoId(UUID moedaVerdeOperacaoId, Long pedidoId);

    @Modifying
    @Query("update PedidoEntity p set p.status = ?1, p.observacao=?3 where p.id = ?2")
    int atualizarStatus(PedidoStatus pedidoStatus, Long pedidoId, String observacao);
}
