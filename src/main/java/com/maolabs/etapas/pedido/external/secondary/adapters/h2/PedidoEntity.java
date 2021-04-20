package com.maolabs.etapas.pedido.external.secondary.adapters.h2;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import com.maolabs.etapas.pedido.internal.application.PedidoStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long clienteId;
    private Long moedasAzul;
    private Long moedasVerde;
    @Type(type = "uuid-char")
    private UUID moedaAzulOperacaoId;
    @Type(type = "uuid-char")
    private UUID moedaVerdeOperacaoId;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    public static PedidoEntity criarNovoPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.clienteId = pedido.getClienteId();
        pedidoEntity.moedasAzul = pedido.getMoedasVerdes();
        pedidoEntity.moedasVerde = pedido.getMoedasAzuis();
        pedidoEntity.status = PedidoStatus.INICIADO;
        return pedidoEntity;
    }

    public Pedido toModel() {
        return Pedido.builder()
                .id(this.id)
                .status(status)
                .clienteId(clienteId)
                .moedasVerdes(moedasAzul)
                .moedasAzuis(moedasVerde)
                .moedaAzulOperacaoId(moedaAzulOperacaoId)
                .moedaVerdeOperacaoId(moedaVerdeOperacaoId)
                .build();
    }
}
