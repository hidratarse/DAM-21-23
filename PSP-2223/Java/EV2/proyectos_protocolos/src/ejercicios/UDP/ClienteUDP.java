package ejercicios.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

class Sender implements Runnable {

    String ip;
    int port;
    DatagramSocket socket;

    public Sender(String ip, int port, DatagramSocket socket) {
        this.ip = ip;
        this.port = port;
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Listo para enviar");
            enviarMensaje(sc, socket, ip, port);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje(Scanner sc, DatagramSocket ds, String ip, int port) throws IOException {
        String message;
        do {
            message = sc.nextLine();
            byte buffer[] = message.getBytes();
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    port);
            ds.send(p);
        } while (!message.equalsIgnoreCase(ClienteUDP.FIN));
        ClienteUDP.finChat = true;
    }
}

class Reciever implements Runnable {

    private final int MAX_LENGTH = 65535;
    DatagramSocket socket;

    public Reciever(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[MAX_LENGTH];
            System.out.println("Esperando respuesta del servidor");
            do {
                DatagramPacket p = new DatagramPacket(buffer, MAX_LENGTH);
                socket.receive(p);
                System.out.println(new String(p.getData(), 0, p.getLength()));
            } while (!ClienteUDP.finChat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ClienteUDP {

    public static String FIN = "chao";
    public static boolean finChat = false;

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
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("PUERTO USADO, ASIGNANDO OTRO");
            socket = new DatagramSocket();
        }
        new Thread(new Sender(ip, port, socket)).start();
        new Thread(new Reciever(socket)).start();
    }
}