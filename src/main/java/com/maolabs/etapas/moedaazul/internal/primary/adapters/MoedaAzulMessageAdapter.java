package com.maolabs.etapas.moedaazul.internal.primary.adapters;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulCompensadaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.exceptions.MoedaAzulInsuficienteException;
import com.maolabs.etapas.moedaazul.internal.primary.ports.MoedaAzulMessagePort;
import com.maolabs.etapas.moedaazul.internal.secondary.ports.MoedaAzulRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MoedaAzulMessageAdapter implements MoedaAzulMessagePort {
    MoedaAzulRepositoryPort moedaAzulRepositoryPort;
    MessagePublisherPort messagePublisherPort;

    @Override
    public void consumir(MoedaAzulConsumirCommand moedaAzulConsumirCommand) {
        final MoedaAzul moedaAzul = moedaAzulRepositoryPort.buscarPorClienteId(moedaAzulConsumirCommand.getClienteId());
        try {
            moedaAzul.consumirPontos(moedaAzulConsumirCommand.getTotalPontos());
            moedaAzulRepositoryPort.atualizar(moedaAzul);
            messagePublisherPort.publishMessage(new MoedaAzulConsumidaEvent(
                    moedaAzulConsumirCommand.getCorrelationId(),
                    moedaAzul,
                    moedaAzulConsumirCommand.getPedidoId(),
                    UUID.randomUUID()
            ));
        } catch (MoedaAzulInsuficienteException exception) {
            messagePublisherPort.publishMessage(new MoedaAzulFalhaConsumoEvent(
                    moedaAzulConsumirCommand.getCorrelationId(),
                    moedaAzulConsumirCommand.getPedidoId(),
                    exception
            ));
        }
    }

    @Override
    public void compensar(MoedaAzulCompensarCommand moedaAzulCompensarCommand) {
        final MoedaAzul moedaAzul = moedaAzulRepositoryPort.buscarPorClienteId(moedaAzulCompensarCommand.getClienteId());
        moedaAzul.adicionarPontos(moedaAzulCompensarCommand.getTotalPontos());
        moedaAzulRepositoryPort.atualizar(moedaAzul);
        messagePublisherPort.publishMessage(new MoedaAzulCompensadaEvent(
                moedaAzulCompensarCommand.getCorrelationId(),
                moedaAzul,
                moedaAzulCompensarCommand.getPedidoId(),
                UUID.randomUUID()
        ));
    }
}
