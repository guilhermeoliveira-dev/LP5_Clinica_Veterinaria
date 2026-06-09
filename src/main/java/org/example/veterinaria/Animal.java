package org.example.veterinaria;

public record Animal(String nome, String raca, String especie, boolean adotado) {

    @Override
    public String toString(){

        return nome;

    }

}
