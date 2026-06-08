package org.example.veterinaria.notificacao;

public interface IAtendimentoSubscriber {

    void receberNotificacao(Notificacao notificacao);

    String getEmail();

}
