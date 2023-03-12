package com.example;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class App {
    public static void main(String[] args) {

        Email email = EmailBuilder.startingBlank()
                .to("Miguel", "miguelsegoviafreeman@gmail.com")
                .from("Miguel", "zangirugaru@gmail.com")
                .withReplyTo("Miguel", "miguelsegoviafreeman@gmail.com")
                .withSubject("Proyecto Spamtoso")
                .withHTMLText("<h1>Hola!!</h1><p>¿Qué tal?</p>")
                .withPlainText("Hola! ¿Qué tal?")
                .buildEmail();
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.educa.madrid.org", 587, "miguel.segoviafreeman", "")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .clearEmailValidator() // turns off email validation
                .buildMailer();
        mailer.sendMail(email);
    }
}