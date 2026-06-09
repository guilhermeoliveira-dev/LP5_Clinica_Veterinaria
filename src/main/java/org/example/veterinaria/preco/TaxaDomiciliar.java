package org.example.veterinaria.preco;

public class TaxaDomiciliar extends PrecoDecorator {

    public TaxaDomiciliar(IPrecoAtendimento precoOriginal) {
        super(precoOriginal);
    }

    @Override
    public double calcular() {
        return precoOriginal.calcular() + 50.0;
    }
}