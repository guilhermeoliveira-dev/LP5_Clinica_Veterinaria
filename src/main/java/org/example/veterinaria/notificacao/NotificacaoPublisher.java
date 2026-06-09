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

        inscrever(TipoNotificacao.ATENDIMENTO_INICIADO, atendimento.getTutor());
        inscrever(TipoNotificacao.ATENDIMENTO_CANCELADO, atendimento.getVeterinario());
        inscrever(TipoNotificacao.ATENDIMENTO_FINALIZADO, Recepcao.get());
    }

    public void emitirNotificacao(Notificacao notificacao) {

        canais.get(notificacao.getTipo()).forEach((observer) -> {
            notificacao.setDestinatario(observer.getEmail());
            observer.receberNotificacao(new Notificacao(notificacao));
        }
        );

    }

    public void inscrever(TipoNotificacao canal, IAtendimentoSubscriber subscriber){
        canais.get(canal).add(subscriber);
    }

    public void desinscrever(TipoNotificacao canal, IAtendimentoSubscriber subscriber){
        canais.get(canal).remove(subscriber);
    }
}
