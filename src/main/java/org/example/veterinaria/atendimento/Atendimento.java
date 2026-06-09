package org.example.veterinaria.atendimento;

import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.example.veterinaria.notificacao.NotificacaoPublisher;
import org.example.veterinaria.preco.IPrecoAtendimento;

public class Atendimento {

    private IEstadoAtendimento estado;

    private final Animal animal;
    private final Tutor tutor;
    private final ServicoVeterinario servico;
    private IPrecoAtendimento preco;
    private Veterinario veterinario;

    private NotificacaoPublisher notificacaoPublisher;

    Atendimento(Animal animal, Tutor tutor, ServicoVeterinario servico, Veterinario veterinario, IPrecoAtendimento preco){
        estado = new EstadoAgendado();
        this.animal = animal;
        this.tutor = tutor;
        this.servico = servico;
        this.veterinario = veterinario;
        this.preco = preco;
        notificacaoPublisher = new NotificacaoPublisher(this);
    }

    public void aplicarAdicionalPreco(IPrecoAtendimento novoDecorador) {
        this.preco = novoDecorador;
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
        return preco.calcular();
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
