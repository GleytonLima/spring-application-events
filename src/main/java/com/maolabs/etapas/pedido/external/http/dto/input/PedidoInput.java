package com.maolabs.etapas.pedido.external.http.dto.input;

import com.maolabs.etapas.pedido.internal.application.Pedido;
import lombok.Data;

@Data
public class PedidoInput {
    Long clienteId;
    Long moedasAzuis;
    Long moedasVerdes;

    public Pedido build() {
        return Pedido
                .builder()
                .clienteId(clienteId)
                .moedasAzuis(moedasAzuis)
                .moedasVerdes(moedasVerdes)
                .build();
    }
}
