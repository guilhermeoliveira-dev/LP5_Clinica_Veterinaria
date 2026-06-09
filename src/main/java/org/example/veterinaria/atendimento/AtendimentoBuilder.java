package org.example.veterinaria.atendimento;

import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.example.veterinaria.preco.*;

public class AtendimentoBuilder {

    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    private boolean temTaxaDomiciliar;
    private boolean temServicoBanho;
    private Veterinario veterinario;

    public AtendimentoBuilder deTutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public AtendimentoBuilder paraAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public AtendimentoBuilder comServico(ServicoVeterinario servico) {
        this.servico = servico;
        return this;
    }

    public AtendimentoBuilder comAtendimentoDomiciliar() {
        this.temTaxaDomiciliar = true;
        return this;
    }

    public AtendimentoBuilder comBanhoPosConsulta() {
        this.temServicoBanho = true;
        return this;
    }

    public AtendimentoBuilder comVeterinario(Veterinario veterinario){
        this.veterinario = veterinario;
        return this;
    }

    public Atendimento build() {
        if (tutor == null || animal == null || servico == null) {
            throw new IllegalArgumentException("Tutor, animal e serviço sao campos obrigatorios.");
        }

        IPrecoAtendimento precoFinal = new PrecoBase(servico.valorBase());

        if (animal.adotado()) {
            precoFinal = new DescontoAdocao(precoFinal);
        }
        if (temTaxaDomiciliar) {
            precoFinal = new TaxaDomiciliar(precoFinal);
        }
        if (temServicoBanho) {
            precoFinal = new AdicionalBanho(precoFinal);
        }

        return new Atendimento(animal, tutor, servico, veterinario, precoFinal);
    }
}