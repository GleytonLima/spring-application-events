package com.maolabs.etapas.pedido.internal.usecases.interfaces;

import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;

public interface PedidoCancelarUseCase {
    void cancelar(PedidoCancelarCommand pedidoCancelarCommand);
}
