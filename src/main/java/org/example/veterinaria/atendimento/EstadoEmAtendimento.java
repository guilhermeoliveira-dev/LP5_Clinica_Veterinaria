package org.example.veterinaria.atendimento;

import org.example.veterinaria.notificacao.Notificacao;
import org.example.veterinaria.notificacao.TipoNotificacao;

public class EstadoEmAtendimento implements IEstadoAtendimento {

    EstadoEmAtendimento(){

    }

    @Override
    public void cancelar(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento em andamento não pode ser cancelado.");
    }

    @Override
    public void iniciarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento em andamento não pode ser iniciado.");
    }

    @Override
    public void finalizarAtendimento(Atendimento contexto) {
        contexto.atualizarEstado(new EstadoFinalizado());
    }

    @Override
    public void emitirNotificacao(Atendimento contexto) {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_INICIADO,
                "O Atendimento "+contexto+" está em andamento."
        );
        contexto.getNotificacaoPublisher().emitirNotificacao(notificacao);
    }
}
