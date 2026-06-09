package org.example.veterinaria.notificacao;

import org.example.log.MockLogger;
import org.example.veterinaria.Animal;
import org.example.veterinaria.ServicoVeterinario;
import org.example.veterinaria.Tutor;
import org.example.veterinaria.Veterinario;
import org.example.veterinaria.atendimento.Atendimento;
import org.example.veterinaria.atendimento.AtendimentoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificacaoPublisherTest {

    private NotificacaoPublisher publisher;
    private SpySubscriber spyTutor;
    private SpySubscriber spyVeterinario;
    private SpySubscriber spyRecepcao;
    private Atendimento atendimentoMock;

    private static class SpySubscriber implements IAtendimentoSubscriber {
        private final List<TipoNotificacao> notificacoesRecebidas = new ArrayList<>();

        public boolean recebeu(TipoNotificacao tipo) {
            return notificacoesRecebidas.contains(tipo);
        }

        public int getQuantidadeNotificacoes() {
            return notificacoesRecebidas.size();
        }

        @Override
        public void receberNotificacao(Notificacao notificacao) {
            notificacoesRecebidas.add(notificacao.getTipo());
        }

        @Override
        public String getEmail() {
            return "";
        }
    }

    @BeforeEach
    void setUp() {





        atendimentoMock = new AtendimentoBuilder()
                .deTutor(new Tutor("Joao", "joao@email.com", MockLogger.get()))
                .paraAnimal(new Animal("Rex", "bull terrier", "cachorro", false))
                .comVeterinario(new Veterinario("marcos@email.com", MockLogger.get()))
                .comServico(new ServicoVeterinario("Vacina", 50.0))
                .build();

        publisher = new NotificacaoPublisher(atendimentoMock);

        spyTutor = new SpySubscriber();
        spyVeterinario = new SpySubscriber();
        spyRecepcao = new SpySubscriber();

        publisher.inscrever(TipoNotificacao.ATENDIMENTO_INICIADO, spyTutor);
        publisher.inscrever(TipoNotificacao.ATENDIMENTO_CANCELADO, spyVeterinario);
        publisher.inscrever(TipoNotificacao.ATENDIMENTO_FINALIZADO, spyRecepcao);


    }

    @Test
    void dadoAtendimentoIniciado_quandoNotificar_entaoApenasTutorRecebeAviso() {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_INICIADO,
                ""
        );
        publisher.emitirNotificacao(notificacao);

        assertTrue(spyTutor.recebeu(TipoNotificacao.ATENDIMENTO_INICIADO));
        assertEquals(0, spyVeterinario.getQuantidadeNotificacoes());
        assertEquals(0, spyRecepcao.getQuantidadeNotificacoes());
    }

    @Test
    void dadoAtendimentoCancelado_quandoNotificar_entaoApenasVeterinarioRecebeAviso() {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_CANCELADO,
                ""
        );
        publisher.emitirNotificacao(notificacao);

        assertTrue(spyVeterinario.recebeu(TipoNotificacao.ATENDIMENTO_CANCELADO));
        assertEquals(0, spyTutor.getQuantidadeNotificacoes());
        assertEquals(0, spyRecepcao.getQuantidadeNotificacoes());
    }

    @Test
    void dadoAtendimentoFinalizado_quandoNotificar_entaoApenasRecepcaoRecebeAviso() {
        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_FINALIZADO,
                ""
        );
        publisher.emitirNotificacao(notificacao);

        assertTrue(spyRecepcao.recebeu(TipoNotificacao.ATENDIMENTO_FINALIZADO));
        assertEquals(0, spyTutor.getQuantidadeNotificacoes());
        assertEquals(0, spyVeterinario.getQuantidadeNotificacoes());
    }

    @Test
    void dadoSubscriberRemovido_quandoNotificar_entaoNaoRecebeAviso() {
        publisher.desinscrever(TipoNotificacao.ATENDIMENTO_INICIADO, spyTutor);

        Notificacao notificacao = new Notificacao(
                TipoNotificacao.ATENDIMENTO_INICIADO,
                ""
        );

        publisher.emitirNotificacao(notificacao);

        assertEquals(0, spyTutor.getQuantidadeNotificacoes());
    }
}