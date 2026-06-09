package org.example.veterinaria.preco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrecoAtendimentoTest {

    @Test
    void dadoPrecoBase_quandoCalcular_entaoRetornaValorCorreto() {
        IPrecoAtendimento preco = new PrecoBase(100.0);

        assertEquals(100.0, preco.calcular());
    }

    @Test
    void dadoPrecoBaseComDesconto_quandoCalcular_entaoRetornaValorReduzido() {
        IPrecoAtendimento preco = new PrecoBase(100.0);
        preco = new DescontoAdocao(preco);

        assertEquals(90.0, preco.calcular());
    }

    @Test
    void dadoPrecoBaseComMultiplasRegras_quandoCalcular_entaoRetornaValorFinalCorreto() {
        IPrecoAtendimento preco = new PrecoBase(100.0);

        preco = new DescontoAdocao(preco);
        preco = new TaxaDomiciliar(preco);
        preco = new AdicionalBanho(preco);

        assertEquals(180.0, preco.calcular(), 0.001);
    }
}