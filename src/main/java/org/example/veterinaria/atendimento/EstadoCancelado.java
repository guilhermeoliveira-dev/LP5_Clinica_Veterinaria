package org.example.veterinaria.atendimento;

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
}
