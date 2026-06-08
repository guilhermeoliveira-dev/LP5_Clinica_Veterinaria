package org.example.veterinaria.atendimento;

import org.example.log.MockLogger;
import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoAgendadoTest {

    Atendimento atendimento;

    @BeforeEach
    void beforeEach(){
        atendimento = new Atendimento(new Animal(), new Tutor("email@email.com", MockLogger.get()), new ServicoVeterinario(), new Veterinario("email@email.com", MockLogger.get()), 10);
    }


    @Test
    void estadoAgendado_tentarCancelar_estadoFicaCancelado(){

        atendimento.cancelar();
        Assertions.assertEquals(EstadoCancelado.class, atendimento.getEstado().getClass(), "O estado deveria ser cancelado.");

    }

    @Test
    void estadoAgendado_tentarIniciarAtendimento_estadoFicaEmAtendimento(){

        atendimento.iniciarAtendimento();
        Assertions.assertEquals(EstadoEmAtendimento.class, atendimento.getEstado().getClass(), "O estado deveria ser cancelado.");

    }

    @Test
    void estadoAgendado_tentarFinalizarAtendimento_retornaExcecao(){

        Exception e = Assertions.assertThrows(IllegalStateException.class, () -> atendimento.finalizarAtendimento(), "Deveria lançar IllegalStateException");

        Assertions.assertEquals("Um atendimento agendado não pode ser finalizado.", e.getMessage(), "A mensagem da exceção está incorreta.");

    }

}
