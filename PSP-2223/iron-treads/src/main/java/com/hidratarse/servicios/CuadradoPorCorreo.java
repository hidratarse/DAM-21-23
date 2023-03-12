package com.hidratarse.servicios;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CuadradoPorCorreo {
    private static final String SERVER = "smtp.educa.madrid.org";
    private static final int PORT = 587;
    private static final String MAILFROM = "miguelsegoviafreeman@gmail.com";
    private static final String SUCCESS = "Correo electrónico enviado exitosamente.";
    private static final String SUBJECT = "EJERCICIO XD";

    public static void main(String[] args) {
        int alto = Integer.parseInt(args[0]);
        int ancho = Integer.parseInt(args[1]);
        String correo = args[2];

        String cuadrado = "";

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                cuadrado += "*";
            }
            cuadrado += "\n";
        }

        mandarMail(cuadrado, correo);
    }

    private static void mandarMail(String cuadrado, String correo) {
        String user = "miguel.segoviafreeman";
        String pass = "";
        Email email = new SimpleEmail();

        email.setHostName(SERVER);
        email.setSmtpPort(PORT);

        email.setAuthentication(user, pass);
        email.setSSLOnConnect(true);
        try {
            email.setFrom(MAILFROM);
            email.setSubject(SUBJECT);
            email.setMsg(cuadrado);
            email.addTo(correo);
            email.send();
            System.out.println(SUCCESS);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}