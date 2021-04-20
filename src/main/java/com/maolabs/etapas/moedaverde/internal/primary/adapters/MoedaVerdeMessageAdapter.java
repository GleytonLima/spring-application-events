package com.maolabs.etapas.moedaverde.internal.primary.adapters;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeCompensadaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.exceptions.MoedaVerdeInsuficienteException;
import com.maolabs.etapas.moedaverde.internal.primary.ports.MoedaVerdeMessagePort;
import com.maolabs.etapas.moedaverde.internal.secondary.ports.MoedaVerdeRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class MoedaVerdeMessageAdapter implements MoedaVerdeMessagePort {
    MoedaVerdeRepositoryPort moedaVerdeRepositoryPort;
    MessagePublisherPort messagePublisherPort;

    @Override
    public void consumir(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            final MoedaVerde moedaVerde = moedaVerdeRepositoryPort.buscarPorClienteId(moedaVerdeConsumirCommand.getClienteId());
            moedaVerde.consumirPontos(moedaVerdeConsumirCommand.getTotalPontos());
            moedaVerdeRepositoryPort.atualizar(moedaVerde);
            messagePublisherPort.publishMessage(new MoedaVerdeConsumidaEvent(
                    moedaVerdeConsumirCommand.getCorrelationId(),
                    moedaVerde,
                    moedaVerdeConsumirCommand.getPedidoId(),
                    UUID.randomUUID()
            ));
        } catch (MoedaVerdeInsuficienteException exception) {
            messagePublisherPort.publishMessage(new MoedaVerdeFalhaConsumoEvent(
                    moedaVerdeConsumirCommand.getCorrelationId(),
                    moedaVerdeConsumirCommand.getPedidoId(),
                    exception
            ));
        }
    }

    @Override
    public void compensar(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand) {
        final MoedaVerde moedaVerde = moedaVerdeRepositoryPort.buscarPorClienteId(moedaVerdeCompensarCommand.getClienteId());
        moedaVerde.adicionarPontos(moedaVerdeCompensarCommand.getTotalPontos());
        moedaVerdeRepositoryPort.atualizar(moedaVerde);
        messagePublisherPort.publishMessage(new MoedaVerdeCompensadaEvent(
                moedaVerdeCompensarCommand.getCorrelationId(),
                moedaVerde,
                moedaVerdeCompensarCommand.getPedidoId(),
                UUID.randomUUID()
        ));
    }
}
