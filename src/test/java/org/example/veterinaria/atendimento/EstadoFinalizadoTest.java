package org.example.veterinaria.atendimento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoFinalizadoTest {

    Atendimento atendimento;

    @BeforeEach
    void beforeEach(){
        atendimento = new Atendimento();
        atendimento.iniciarAtendimento();
        atendimento.finalizarAtendimento();
    }

    @Test
    void estadoFinalizado_tentarCancelar_retornaExcecao(){

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> {
            atendimento.cancelar();
        }, "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento já finalizado não pode ser cancelado.", e.getMessage(), "A mensagem da exceção está incorreta.");

    }

    @Test
    void estadoFinalizado_tentarIniciarAtendimento_retornaExcecao(){

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> {
            atendimento.iniciarAtendimento();
        }, "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento já finalizado não pode ser iniciado.", e.getMessage(), "A mensagem da exceção está incorreta.");

    }

    @Test
    void estadoFinalizado_tentarFinalizarAtendimento_retornaExcecao() {

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> {
            atendimento.finalizarAtendimento();
        }, "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento já finalizado não pode ser finalizado.", e.getMessage(), "A mensagem da exceção está incorreta.");
    }
}
