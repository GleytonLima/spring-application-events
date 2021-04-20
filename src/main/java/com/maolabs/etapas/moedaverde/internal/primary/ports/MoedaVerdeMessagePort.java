package com.maolabs.etapas.moedaverde.internal.primary.ports;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;

public interface MoedaVerdeMessagePort {
    void consumir(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand);

    void compensar(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand);
}
