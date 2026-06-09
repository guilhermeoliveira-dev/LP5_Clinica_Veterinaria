package org.example.veterinaria.preco;

public abstract class PrecoDecorator implements IPrecoAtendimento {

    protected final IPrecoAtendimento precoOriginal;

    public PrecoDecorator(IPrecoAtendimento precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    @Override
    public double calcular() {
        return precoOriginal.calcular();
    }
}