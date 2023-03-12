package com.hidratarse.comunicacion;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static String FIN = "chao";
    public static volatile boolean finChat = false;

    public static void main(String[] args) throws SocketException {
        String ip = null;
        int port = 0;

        if (args.length <= 1) {
            System.out.println("Parametros [ip] [Puerto]");
            System.exit(0);
        } else {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        SocketUDP socket = new SocketUDP(port);

        new Thread(new Sender(ip, port, socket)).start();
        new Thread(new Receiver(socket)).start();
    }
}

class Receiver implements Runnable {
    private SocketUDP socket;

    public Receiver(SocketUDP socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Esperando respuesta del servidor");
            do {
                String message = this.socket.recibirMensaje();
                System.out.println(message);
            } while (!ClienteUDP.finChat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sender implements Runnable {
    String ip;
    int port;
    SocketUDP socket;

    public Sender(String ip, int port, SocketUDP socket) {
        this.ip = ip;
        this.port = port;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Listo para enviar");
            String message;
            do {
                message = sc.nextLine();
                this.socket.enviarMensaje(message, this.ip, this.port);
            } while (!message.equalsIgnoreCase(ClienteUDP.FIN));
            ClienteUDP.finChat = true;
            this.socket.cerrar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}