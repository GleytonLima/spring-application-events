package com.maolabs.etapas.moedaverde.internal.usecases;

import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;
import com.maolabs.etapas.moedaverde.internal.primary.ports.MoedaVerdeMessagePort;
import org.springframework.stereotype.Service;

@Service
public class MoedaVerdeConsumirUseCaseImpl implements MoedaVerdeConsumirUseCase {
    MoedaVerdeMessagePort programaPontosAMensagemConsumerAdapter;

    public void consumir(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand) {
        programaPontosAMensagemConsumerAdapter.consumir(moedaVerdeConsumirCommand);
    }

    @Override
    public void consumir(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand) {
        programaPontosAMensagemConsumerAdapter.compensar(moedaVerdeCompensarCommand);
    }
}
