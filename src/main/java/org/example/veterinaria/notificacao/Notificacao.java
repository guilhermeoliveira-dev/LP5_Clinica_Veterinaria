package org.example.veterinaria.notificacao;

public final class Notificacao {
    private final TipoNotificacao tipo;
    private final String corpo;
    private String destinatario;

    public Notificacao(TipoNotificacao tipo, String corpo) {
        this.tipo = tipo;
        this.corpo = corpo;
    }

    public Notificacao(Notificacao notificacao) {
        this.tipo = notificacao.tipo;
        this.corpo = notificacao.corpo;
        this.destinatario = notificacao.destinatario;
    }


    public String formatarEmail(){

        if(destinatario == null || destinatario.isBlank()){
            throw new NullPointerException("A notificação não pode ser enviada sem um e-mail de destinatário.");
        }

        return "De: veterinaria@gmail.com\n" +
                "Para: " + destinatario + "\n" +
                "Assunto: " + tipo.getAssunto() + "\n\n" +
                corpo;
    }

    public TipoNotificacao getTipo() {
        return tipo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setDestinatario(String email){
        this.destinatario = email;
    }




}
