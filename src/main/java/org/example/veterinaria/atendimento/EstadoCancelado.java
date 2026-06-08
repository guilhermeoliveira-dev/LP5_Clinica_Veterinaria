package org.example.veterinaria.atendimento;

import org.example.veterinaria.notificacao.Notificacao;
import org.example.veterinaria.notificacao.TipoNotificacao;

public class EstadoCancelado implements IEstadoAtendimento {

    EstadoCancelado(){

    }

    @Override
    public void cancelar(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento cancelado não pode ser cancelado.");
    }

    @Override
    public void iniciarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento cancelado não pode ser iniciado.");
    }

    @Override
    public void finalizarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento cancelado não pode ser finalizado.");
    }

    @Override
    public void emitirNotificacao(Atendimento contexto) {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_CANCELADO,
                "O Atendimento "+contexto+" foi cancelado."
        );
        contexto.getNotificacaoPublisher().emitirNotificacao(notificacao);
    }
}
