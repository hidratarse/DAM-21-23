package com.hidratarse.comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCPMultiHilo {
    public static void main(String[] args) {
        String ip = null;
        int port = 0;

        if (args.length <= 1) {
            System.out.println("Parametros [ip] [Puerto]");
            System.exit(0);
        } else {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        try {
            Socket socket = new Socket(ip, port);
            new Thread(new SenderTCP(socket)).start();
            new Thread(new ReceiverTCP(socket)).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SenderTCP implements Runnable {
    private final Socket SOCKET;

    public SenderTCP(Socket con) {
        this.SOCKET = con;
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(SOCKET.getOutputStream());
                Scanner scanner = new Scanner(System.in);) {
            while (true) {
                String message = scanner.nextLine();
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiverTCP implements Runnable {
    private final Socket SOCKET;

    public ReceiverTCP(Socket con) {
        this.SOCKET = con;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(SOCKET.getInputStream())) {
            while (true) {
                System.out.println("S: " + in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}