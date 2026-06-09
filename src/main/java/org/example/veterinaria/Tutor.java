package org.example.veterinaria;

import org.example.log.ILogger;
import org.example.log.LogType;
import org.example.veterinaria.notificacao.IAtendimentoSubscriber;
import org.example.veterinaria.notificacao.Notificacao;

public class Tutor implements IAtendimentoSubscriber {

    private String email;
    private String nome;
    private final ILogger logger;


    public Tutor(String nome, String email, ILogger logger){
        this.email = email;
        this.logger = logger;
        this.nome = nome;
    }

    @Override
    public void receberNotificacao(Notificacao notificacao) {
        logger.log(LogType.SYSTEM, "Enviando email para tutor: " + email + "\n" + notificacao.formatarEmail());
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString(){

        return email;

    }

}
