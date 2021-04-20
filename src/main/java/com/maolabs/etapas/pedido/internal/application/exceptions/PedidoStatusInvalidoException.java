package com.maolabs.etapas.pedido.internal.application.exceptions;

public class PedidoStatusInvalidoException extends RuntimeException {
    public PedidoStatusInvalidoException(String message) {
        super(message);
    }
}
