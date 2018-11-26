package com.thoughtworks.aceleradora.email.controllers;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {

    private JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/email")
    public void sendMail() {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setText("Nova solicitação recebida!");
        mensagem.setTo("diovane.m.mendes@gmail.com");
        mensagem.setFrom("diovane.m.mendes@gmail.com");

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
