package com.thoughtworks.aceleradora.email.component;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {

    private JavaMailSender mailSender;

    public EmailComponent(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void notificaCasa() {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setText("Nova solicitação recebida!");
        mensagem.setTo("aceleradora14@gmail.com");
        mensagem.setFrom("aceleradora14@gmail.com");

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notificaHospital() {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setText("Status de uma solicitação foi alterado!");
        mensagem.setTo("aceleradora14@gmail.com");
        mensagem.setFrom("aceleradora14@gmail.com");

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
