package org.example.veterinaria.atendimento;

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
}
