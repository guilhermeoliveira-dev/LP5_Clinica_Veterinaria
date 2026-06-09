package org.example.veterinaria;

public record ServicoVeterinario(String servico, double valorBase) {

    @Override
    public String toString(){

        return servico;

    }

}
