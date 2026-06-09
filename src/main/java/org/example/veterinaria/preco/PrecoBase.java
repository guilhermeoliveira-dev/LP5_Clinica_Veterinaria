package org.example.veterinaria.preco;

public class PrecoBase implements IPrecoAtendimento {

    private final double valorBase;

    public PrecoBase(double valorBase) {
        this.valorBase = valorBase;
    }

    @Override
    public double calcular() {
        return valorBase;
    }
}