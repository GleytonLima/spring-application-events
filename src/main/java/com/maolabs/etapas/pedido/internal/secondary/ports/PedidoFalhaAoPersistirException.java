package com.maolabs.etapas.pedido.internal.secondary.ports;

public class PedidoFalhaAoPersistirException extends Exception {
    public PedidoFalhaAoPersistirException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return getMessage() + " " + getCause();
    }
}
