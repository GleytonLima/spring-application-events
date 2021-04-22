package com.maolabs.etapas.moedaverde.internal.primary.adapters;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerde;
import com.maolabs.etapas.moedaverde.internal.application.MoedaVerdeOperacaoTipo;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeCompensarCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.commands.MoedaVerdeConsumirCommand;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeCompensadaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeConsumidaEvent;
import com.maolabs.etapas.moedaverde.internal.application.mensagens.events.MoedaVerdeFalhaConsumoEvent;
import com.maolabs.etapas.moedaverde.internal.primary.ports.MoedaVerdeMessagePort;
import com.maolabs.etapas.moedaverde.internal.secondary.ports.MoedaVerdeRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MoedaVerdeMessageAdapter implements MoedaVerdeMessagePort {
    MoedaVerdeRepositoryPort moedaVerdeRepositoryPort;
    MessagePublisherPort messagePublisherPort;

    @Override
    public void consumir(MoedaVerdeConsumirCommand moedaVerdeConsumirCommand) {
        try {
            if (Math.random() > 0.5) {
                throw new Exception("Falha proposital de 50% de chance no consumo da moeda verde");
            }
            moedaVerdeRepositoryPort.existsByClienteId(moedaVerdeConsumirCommand.getClienteId(), moedaVerdeConsumirCommand.getPedidoId());
            final MoedaVerde moedaVerde = new MoedaVerde(moedaVerdeConsumirCommand.getClienteId(),
                    moedaVerdeConsumirCommand.getPedidoId(),
                    moedaVerdeConsumirCommand.getTotalPontos(),
                    UUID.randomUUID(),
                    MoedaVerdeOperacaoTipo.DEBITO);
            moedaVerdeRepositoryPort.cadastrar(moedaVerde);
            messagePublisherPort.publishMessage(new MoedaVerdeConsumidaEvent(
                    moedaVerdeConsumirCommand.getCorrelationId(),
                    moedaVerde,
                    moedaVerdeConsumirCommand.getPedidoId(),
                    moedaVerde.getUuid()
            ));
        } catch (Exception exception) {
            messagePublisherPort.publishMessage(new MoedaVerdeFalhaConsumoEvent(
                    moedaVerdeConsumirCommand.getCorrelationId(),
                    moedaVerdeConsumirCommand.getPedidoId(),
                    exception
            ));
        }
    }

    @Override
    public void compensar(MoedaVerdeCompensarCommand moedaVerdeCompensarCommand) {
        final MoedaVerde moedaVerde = new MoedaVerde(moedaVerdeCompensarCommand.getClienteId(),
                moedaVerdeCompensarCommand.getPedidoId(),
                moedaVerdeCompensarCommand.getTotalPontos(),
                UUID.randomUUID(),
                MoedaVerdeOperacaoTipo.COMPENSACAO);
        moedaVerdeRepositoryPort.cadastrar(moedaVerde);
        messagePublisherPort.publishMessage(new MoedaVerdeCompensadaEvent(
                moedaVerdeCompensarCommand.getCorrelationId(),
                moedaVerde,
                moedaVerdeCompensarCommand.getPedidoId(),
                moedaVerde.getUuid()
        ));
    }
}
