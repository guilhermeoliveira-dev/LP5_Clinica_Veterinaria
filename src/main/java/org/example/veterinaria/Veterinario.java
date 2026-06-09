package org.example.veterinaria;

import org.example.log.ILogger;
import org.example.log.LogType;
import org.example.veterinaria.notificacao.IAtendimentoSubscriber;
import org.example.veterinaria.notificacao.Notificacao;

public class Veterinario implements IAtendimentoSubscriber {

    private String email;
    private ILogger logger;

    public Veterinario(String email, ILogger logger){
        this.email = email;
        this.logger = logger;
    }

    @Override
    public void receberNotificacao(Notificacao notificacao) {
        logger.log(LogType.SYSTEM, "Enviando email para veterinario: " + email + "\n" + notificacao.formatarEmail());
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){

        return email;

    }
}
