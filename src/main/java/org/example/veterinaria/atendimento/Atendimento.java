package org.example.veterinaria.atendimento;

public class Atendimento {

    private IEstadoAtendimento estado;

    public Atendimento(){
        estado = new EstadoAgendado();
    }

    public void atualizarEstado(IEstadoAtendimento estado){
        this.estado = estado;
    }
    public IEstadoAtendimento getEstado(){
        return estado;
    }

    public void cancelar(){
        estado.cancelar(this);
    }
    public void iniciarAtendimento(){
        estado.iniciarAtendimento(this);
    }
    public void finalizarAtendimento(){
        estado.finalizarAtendimento(this);
    }

}
