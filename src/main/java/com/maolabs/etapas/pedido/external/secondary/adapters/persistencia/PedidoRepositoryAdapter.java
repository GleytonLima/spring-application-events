package com.maolabs.etapas.pedido.external.secondary.adapters.persistencia;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.PedidoStatus;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoFalhaAoPersistirException;
import com.maolabs.etapas.pedido.internal.secondary.ports.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {
    private final PedidoRepository pedidoRepository;


    @Override
    public Pedido criarNovoPedido(Pedido pedido) throws PedidoFalhaAoPersistirException {
        try {
            final PedidoEntity save = pedidoRepository.save(PedidoEntity.criarNovoPedido(pedido));
            pedido.definirID(save.getId());
            return pedido;
        } catch (Exception e) {
            throw new PedidoFalhaAoPersistirException("Falha ao salvar no banco de dados", e);
        }
    }

    @Override
    public void atualizarMoedaAzulOperacaoId(Pedido pedido) {
        pedidoRepository.atualizarMoedaAzulOperacaoId(pedido.getMoedaAzulOperacaoId(), pedido.getId());
    }

    @Override
    public void atualizarMoedaVerdeOperacaoId(Pedido pedido) {
        pedidoRepository.atualizarMoedaVerdeOperacaoId(pedido.getMoedaVerdeOperacaoId(), pedido.getId());
    }

    @Override
    public void atualizarStatus(PedidoStatus status, Long id, String observacao) {
        pedidoRepository.atualizarStatus(status, id, observacao);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).get().toModel();
    }
}
