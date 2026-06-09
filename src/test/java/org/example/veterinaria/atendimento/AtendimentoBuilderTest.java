package org.example.veterinaria.atendimento;

import org.example.log.MockLogger;
import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtendimentoBuilderTest {

    @Test
    void dadoCamposObrigatorios_quandoBuild_entaoCriaAtendimentoComPrecoBase() {
        Tutor tutor = new Tutor("Joao", "email@email.com", MockLogger.get());
        Animal animal = new Animal("Rex", "bull terrier", "cachorro", false);
        ServicoVeterinario servico = new ServicoVeterinario("Consulta", 100.0);

        Atendimento atendimento = new AtendimentoBuilder()
                .deTutor(tutor)
                .paraAnimal(animal)
                .comServico(servico)
                .build();

        assertEquals(100.0, atendimento.getValorFinal());
        assertEquals("Joao", atendimento.getTutor().getNome());
        assertEquals("Rex", atendimento.getAnimal().nome());
    }

    @Test
    void dadoFaltaDeCamposObrigatorios_quandoBuild_entaoLancaExcecao() {
        Tutor tutor = new Tutor("Joao", "email@email.com", MockLogger.get());

        assertThrows(IllegalArgumentException.class, () -> {
            new AtendimentoBuilder()
                    .deTutor(tutor)
                    .build();
        });
    }

    @Test
    void dadoTodosDecoradores_quandoBuild_entaoAplicaRegrasDePrecoCorretamente() {
        Tutor tutor = new Tutor("Joao", "email@email.com", MockLogger.get());
        Animal animal = new Animal("Rex", "bull terrier", "cachorro", true);
        ServicoVeterinario servico = new ServicoVeterinario("Consulta", 100.0);

        Atendimento atendimento = new AtendimentoBuilder()
                .deTutor(tutor)
                .paraAnimal(animal)
                .comServico(servico)
                .comAtendimentoDomiciliar()
                .comBanhoPosConsulta()
                .build();

        assertEquals(180.0, atendimento.getValorFinal(), 0.001);
    }
}