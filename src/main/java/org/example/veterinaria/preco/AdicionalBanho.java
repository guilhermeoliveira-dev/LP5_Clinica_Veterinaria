package org.example.veterinaria.preco;

public class AdicionalBanho extends PrecoDecorator {

    public AdicionalBanho(IPrecoAtendimento precoOriginal) {
        super(precoOriginal);
    }

    @Override
    public double calcular() {
        return precoOriginal.calcular() + 40.0;
    }
}