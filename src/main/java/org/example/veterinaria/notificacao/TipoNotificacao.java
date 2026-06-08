package org.example.veterinaria.notificacao;

import java.util.Locale;

public enum TipoNotificacao {

    ATENDIMENTO_CRIADO,
    ATENDIMENTO_INICIADO,
    ATENDIMENTO_CANCELADO,
    ATENDIMENTO_FINALIZADO;

    public String getAssunto(){

        String noUnderline = name().replace("_", " ");

        String lowercase = noUnderline.toLowerCase(Locale.ROOT);

        return lowercase.substring(0, 1).toUpperCase() + lowercase.substring(1);

    }
}
