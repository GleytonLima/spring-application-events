package com.maolabs.etapas.moedaverde.internal.usecases;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;

public interface MoedaVerdeConsumirUseCase {
    void consumir(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand);
    void consumir(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand);
}
