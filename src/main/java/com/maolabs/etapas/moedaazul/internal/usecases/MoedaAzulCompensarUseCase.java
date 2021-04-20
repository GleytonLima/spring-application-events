package com.maolabs.etapas.moedaazul.internal.usecases;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;

public interface MoedaAzulCompensarUseCase {
    void consumir(MoedaAzulCompensarCommand moedaAzulCompensarCommand);
}
