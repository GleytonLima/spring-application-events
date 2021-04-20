package com.maolabs.etapas.moedaazul.internal.primary.ports;

import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;

public interface MoedaAzulMessagePort {
    void consumir(MoedaAzulConsumirCommand moedaAzulConsumirCommand);

    void compensar(MoedaAzulCompensarCommand moedaAzulCompensarCommand);
}
