package org.example.veterinaria.preco;

public class DescontoAdocao extends PrecoDecorator {

    public DescontoAdocao(IPrecoAtendimento precoOriginal) {
        super(precoOriginal);
    }

    @Override
    public double calcular() {
        return precoOriginal.calcular() * 0.90;
    }
}