package com.maolabs.etapas.moedaazul.internal.usecases;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;
import com.maolabs.etapas.moedaazul.internal.primary.ports.MoedaAzulMessagePort;
import org.springframework.stereotype.Service;

@Service
public class MoedaAzulConsumirUseCaseImpl implements MoedaAzulConsumirUseCase {
    MoedaAzulMessagePort moedaAzulMessagePort;

    public void consumir(MoedaAzulConsumirCommand moedaAzulConsumirCommand) {
        moedaAzulMessagePort.consumir(moedaAzulConsumirCommand);
    }
}
