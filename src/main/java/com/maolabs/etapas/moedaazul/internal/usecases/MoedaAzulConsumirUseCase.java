package com.maolabs.etapas.moedaazul.internal.usecases;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;

public interface MoedaAzulConsumirUseCase {
    void consumir(MoedaAzulConsumirCommand moedaAzulConsumirCommand);
}
