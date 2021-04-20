package com.maolabs.etapas.pedido.internal.secondary.ports;

import com.maolabs.etapas.pedido.internal.application.mensagens.commands.PedidoCancelarCommand;
import com.maolabs.etapas.pedido.internal.application.mensagens.events.*;

public interface PedidoMessageAdapterPort {
    void publicarEventoPedidoCriado(PedidoCriadoEvent pedidoCriadoEvent);

    void publicarEventoFalhaPersitir(PedidoFalhaPersitirEvent pedidoFalhaPersitirEvent);

    void consumirPedidoMoedaAzulConfirmadaEvent(PedidoMoedaAzulConfirmadaMessage pedidoMoedaAzulConfirmadaEvent);

    void consumirPedidoMoedaVerdeConfirmadaEvent(PedidoMoedaVerdeConfirmadaMessage pedidoMoedaVerdeConfirmadaEvent);

    void consumirPedidoMoedaAzulAtualizadaEvent(PedidoMoedaAzulAtualizadaMessage pedidoMoedaAzulAtualizadaEvent);

    void consumirPedidoMoedaVerdeAtualizadaEvent(PedidoMoedaVerdeAtualizadaMessage pedidoMoedaVerdeAtualizadaEvent);

    void processarPedidoCancelarCommand(PedidoCancelarCommand pedidoCancelarCommand);
}
