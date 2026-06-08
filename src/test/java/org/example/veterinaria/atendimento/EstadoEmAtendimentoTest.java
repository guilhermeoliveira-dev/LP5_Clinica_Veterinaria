package org.example.veterinaria.atendimento;

import org.example.log.MockLogger;
import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoEmAtendimentoTest {

    Atendimento atendimento;

    @BeforeEach
    void beforeEach(){
        atendimento = new Atendimento(new Animal(), new Tutor("email@email.com", MockLogger.get()), new ServicoVeterinario(), new Veterinario("email@email.com", MockLogger.get()), 10);
        atendimento.iniciarAtendimento();
    }

    @Test
    void estadoEmAtendimento_tentarCancelar_retornaExcecao(){

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> atendimento.cancelar(), "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento em andamento não pode ser cancelado.", e.getMessage(), "A mensagem da exceção está incorreta.");

    }

    @Test
    void estadoEmAtendimento_tentarIniciarAtendimento_retornaExcecao(){

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> atendimento.iniciarAtendimento(), "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento em andamento não pode ser iniciado.", e.getMessage(), "A mensagem da exceção está incorreta.");


    }

    @Test
    void estadoEmAtendimento_tentarFinalizarAtendimento_estadoFicaFinalizado(){

        atendimento.finalizarAtendimento();
        Assertions.assertEquals(EstadoFinalizado.class, atendimento.getEstado().getClass(), "O estado deveria ser finalizado.");

    }
}
