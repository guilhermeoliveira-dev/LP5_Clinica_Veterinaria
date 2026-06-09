package org.example.veterinaria.atendimento;

public class EstadoAgendado implements IEstadoAtendimento {

    protected EstadoAgendado(){

    }

    @Override
    public void cancelar(Atendimento contexto) {
        contexto.atualizarEstado(new EstadoCancelado());
    }

    @Override
    public void iniciarAtendimento(Atendimento contexto) {
        contexto.atualizarEstado(new EstadoEmAtendimento());
    }

    @Override
    public void finalizarAtendimento(Atendimento contexto) {
        throw new IllegalStateException("Um atendimento agendado não pode ser finalizado.");
    }

    @Override
    public void emitirNotificacao(Atendimento contexto) {

    }
}
