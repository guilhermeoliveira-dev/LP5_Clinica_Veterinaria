package org.example.veterinaria.atendimento;

import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.example.veterinaria.notificacao.NotificacaoPublisher;

public class Atendimento {

    private IEstadoAtendimento estado;

    private Animal animal;
    private Tutor tutor;
    private ServicoVeterinario servico;
    private final double valorBase;
    private Veterinario veterinario;

    private NotificacaoPublisher notificacaoPublisher;

    public Atendimento(Animal animal, Tutor tutor, ServicoVeterinario servico, Veterinario veterinario, double valorBase){
        estado = new EstadoAgendado();
        this.animal = animal;
        this.tutor = tutor;
        this.servico = servico;
        this.veterinario = veterinario;
        this.valorBase = valorBase;
        notificacaoPublisher = new NotificacaoPublisher(this);
    }

    public void atualizarEstado(IEstadoAtendimento estado){
        this.estado = estado;
        emitirNotificacao();
    }



    public void cancelar(){
        estado.cancelar(this);
    }
    public void iniciarAtendimento(){
        estado.iniciarAtendimento(this);
    }
    public void finalizarAtendimento(){
        estado.finalizarAtendimento(this);
    }
    private void emitirNotificacao() {
        estado.emitirNotificacao(this);
    }



    public Veterinario getVeterinario() {
        return veterinario;
    }

    public IEstadoAtendimento getEstado(){
        return estado;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }

    public NotificacaoPublisher getNotificacaoPublisher() {
        return notificacaoPublisher;
    }

    public double getValorFinal(){
        return valorBase;
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder()
                .append("Serviço : \"").append(servico.toString()).append("\", ")
                .append("Animal: \"").append(animal.toString()).append("\", ")
                .append("Tutor: \"").append(animal.toString()).append("\", ")
                .append("Valor: R$").append(getValorFinal()).append(".");
        return b.toString();
    }
}
