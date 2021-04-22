package com.maolabs.etapas.moedaazul.internal.primary.adapters;

import com.maolabs.etapas.MessagePublisherPort;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzul;
import com.maolabs.etapas.moedaazul.internal.application.MoedaAzulOperacaoTipo;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulCompensarCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.commands.MoedaAzulConsumirCommand;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulCompensadaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulConsumidaEvent;
import com.maolabs.etapas.moedaazul.internal.application.mensagens.events.MoedaAzulFalhaConsumoEvent;
import com.maolabs.etapas.moedaazul.internal.primary.ports.MoedaAzulMessagePort;
import com.maolabs.etapas.moedaazul.internal.secondary.ports.MoedaAzulRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MoedaAzulMessageAdapter implements MoedaAzulMessagePort {
    MoedaAzulRepositoryPort moedaAzulRepositoryPort;
    MessagePublisherPort messagePublisherPort;

    @Override
    public void consumir(MoedaAzulConsumirCommand moedaAzulConsumirCommand) {

        try {
            moedaAzulRepositoryPort.creditarSeNovoCliente(moedaAzulConsumirCommand.getClienteId(), moedaAzulConsumirCommand.getPedidoId());
            var moedaAzul = new MoedaAzul(
                    moedaAzulConsumirCommand.getClienteId(),
                    moedaAzulConsumirCommand.getPedidoId(),
                    moedaAzulConsumirCommand.getTotalPontos(),
                    UUID.randomUUID(),
                    MoedaAzulOperacaoTipo.DEBITO
            );
            moedaAzulRepositoryPort.cadastrar(moedaAzul);
            messagePublisherPort.publishMessage(new MoedaAzulConsumidaEvent(
                    moedaAzulConsumirCommand.getCorrelationId(),
                    moedaAzul,
                    moedaAzulConsumirCommand.getPedidoId(),
                    moedaAzul.getUuid()
            ));
        } catch (Exception exception) {
            log.error("Falha ao consumir moeda azul: " + exception.getLocalizedMessage());
            messagePublisherPort.publishMessage(new MoedaAzulFalhaConsumoEvent(
                    moedaAzulConsumirCommand.getCorrelationId(),
                    moedaAzulConsumirCommand.getPedidoId(),
                    exception
            ));
        }
    }

    @Override
    public void compensar(MoedaAzulCompensarCommand moedaAzulCompensarCommand) {
        var moedaAzul = new MoedaAzul(
                moedaAzulCompensarCommand.getClienteId(),
                moedaAzulCompensarCommand.getPedidoId(),
                moedaAzulCompensarCommand.getTotalPontos(),
                UUID.randomUUID(),
                MoedaAzulOperacaoTipo.COMPENSACAO
        );
        moedaAzulRepositoryPort.cadastrar(moedaAzul);
        messagePublisherPort.publishMessage(new MoedaAzulCompensadaEvent(
                moedaAzulCompensarCommand.getCorrelationId(),
                moedaAzul,
                moedaAzul.getPedidoId(),
                moedaAzul.getUuid()
        ));
    }
}
