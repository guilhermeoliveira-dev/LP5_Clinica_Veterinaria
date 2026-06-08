package org.example.veterinaria;

import org.example.log.ILogger;
import org.example.log.LogType;
import org.example.log.MockLogger;
import org.example.veterinaria.notificacao.IAtendimentoSubscriber;
import org.example.veterinaria.notificacao.Notificacao;

public class Recepcao implements IAtendimentoSubscriber {

    private static final Recepcao instance = new Recepcao("secretaria.veterinaria@gmail.com", MockLogger.get());
    private String email;
    private ILogger logger;

    public Recepcao(String email, ILogger logger){
        this.email = email;
        this.logger = logger;
    }

    public static IAtendimentoSubscriber get() {
        return instance;
    }

    @Override
    public void receberNotificacao(Notificacao notificacao) {
        logger.log(LogType.SYSTEM, "Enviando email para secretaria: " + email + "\n" + notificacao.formatarEmail());
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setLogger(ILogger logger){
        this.logger = logger;
    }

}
