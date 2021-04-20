package com.maolabs.etapas.moedaverde.external.secondary.adapters.mensagem;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;
import com.maolabs.etapas.moedaverde.internal.primary.ports.MoedaVerdeMessagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MoedaVerdeMessageController {
    MoedaVerdeMessagePort moedaVerdeMessagePort;

    @EventListener
    public void handle(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand) {
        moedaVerdeMessagePort.consumir(moedaVerdeConsumirCommand);
    }

    @EventListener
    public void handle(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand) {
        moedaVerdeMessagePort.compensar(moedaVerdeCompensarCommand);
    }
}
