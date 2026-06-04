package org.example.veterinaria.atendimento;

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
}
