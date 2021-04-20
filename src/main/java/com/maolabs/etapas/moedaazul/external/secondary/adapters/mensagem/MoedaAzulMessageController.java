package com.maolabs.etapas.moedaazul.external.secondary.adapters.mensagem;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;
import com.maolabs.etapas.moedaazul.internal.primary.ports.MoedaAzulMessagePort;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MoedaAzulMessageController {
    MoedaAzulMessagePort moedaAzulMessagePort;

    @EventListener
    public void handle(MoedaAzulConsumirCommand moedaVerdeConsumirCommand) {
        moedaAzulMessagePort.consumir(moedaVerdeConsumirCommand);
    }

    @EventListener
    public void handle(MoedaAzulCompensarCommand moedaAzulCompensarCommand) {
        moedaAzulMessagePort.compensar(moedaAzulCompensarCommand);
    }
}
