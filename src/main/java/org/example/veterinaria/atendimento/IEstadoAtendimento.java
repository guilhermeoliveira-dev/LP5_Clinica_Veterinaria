package org.example.veterinaria.atendimento;

public interface IEstadoAtendimento {

    void cancelar(Atendimento contexto);
    void iniciarAtendimento(Atendimento contexto);
    void finalizarAtendimento(Atendimento contexto);

}
