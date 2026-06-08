package org.example.veterinaria.notificacao;

import org.example.veterinaria.Recepcao;
import org.example.veterinaria.atendimento.Atendimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificacaoPublisher {

    private final Map<TipoNotificacao, List<IAtendimentoSubscriber>> canais;

    public NotificacaoPublisher(Atendimento atendimento) {

        canais = new ConcurrentHashMap<>();
        configurarCanais(atendimento);
    }

    private void configurarCanais(Atendimento atendimento) {

        Arrays.stream(TipoNotificacao.values()).forEach((tipo) -> canais.put(tipo, new ArrayList<>()));

        canais.get(TipoNotificacao.ATENDIMENTO_INICIADO).add(atendimento.getTutor());
        canais.get(TipoNotificacao.ATENDIMENTO_CANCELADO).add(atendimento.getVeterinario());
        canais.get(TipoNotificacao.ATENDIMENTO_FINALIZADO).add(Recepcao.get());
    }

    public void emitirNotificacao(Notificacao notificacao) {

        canais.get(notificacao.getTipo()).forEach((observer) -> {
            notificacao.setDestinatario(observer.getEmail());
            observer.receberNotificacao(new Notificacao(notificacao));
        }
        );

    }
}
