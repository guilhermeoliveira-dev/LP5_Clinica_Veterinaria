package org.example.veterinaria.atendimento;

import org.example.veterinaria.notificacao.Notificacao;
import org.example.veterinaria.notificacao.TipoNotificacao;

public class EstadoFinalizado implements IEstadoAtendimento {

    EstadoFinalizado(){

    }

    @Override
    public void cancelar(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento já finalizado não pode ser cancelado.");
    }

    @Override
    public void iniciarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento já finalizado não pode ser iniciado.");
    }

    @Override
    public void finalizarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento já finalizado não pode ser finalizado.");
    }

    @Override
    public void emitirNotificacao(Atendimento contexto) {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_FINALIZADO,
                "O Atendimento "+contexto+" foi finalizado."
        );
        contexto.getNotificacaoPublisher().emitirNotificacao(notificacao);
    }
}
