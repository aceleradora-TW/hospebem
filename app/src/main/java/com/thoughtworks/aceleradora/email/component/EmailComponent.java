package com.thoughtworks.aceleradora.email.component;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {

    private JavaMailSender mailSender;

    public EmailComponent(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void notificaCasa(Solicitacao solicitacao) {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setSubject("Nova solicitação recebida!");

        mensagem.setText("Nova solicitação recebida!\n" + "\n" +
        "- Hospede: " + solicitacao.getNome() + "\n" +
        "- Data de Nascimento: " + solicitacao.getDataNascimento() + "\n" +
        "\n ========================== \n" + "\n" +
        " Dados da solicitante: " + "\n" +
        "- Nome: " + solicitacao.getNomeSolicitante() + "\n" +
        "- E-mail: " + solicitacao.getEmail() + "\n" +
        "- Hospital de Referência: " + solicitacao.getHospitalReferencia() + "\n" +
        "- Telefone: " + solicitacao.getTelefoneSolicitante());

        mensagem.setTo("hospebem@gmail.com");
        mensagem.setFrom("hospebem@gmail.com");

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notificaHospital(Solicitacao solicitacao) {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setSubject("Status de uma solicitação foi alterado!");
        mensagem.setText("Status de uma solicitação de: "+ solicitacao.getNome() + " foi alterado para: " +
                solicitacao.getStatus() + "!");
        mensagem.setTo(solicitacao.getEmail());
        mensagem.setFrom(solicitacao.getEmail());

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
