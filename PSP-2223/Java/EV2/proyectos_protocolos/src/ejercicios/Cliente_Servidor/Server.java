package ejercicios.Cliente_Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public final static int PUERTO = 8888;
    private final static String SALUDO = "BIENVENIDO AL SERVIDOR MONDONGO, NO TROLLEAR PLS :^)" + "\n";
    public final static String MENSAJE_FIN = "chao";

    public static void main(String[] args) {
        ServerSocket server;
        String message = "";
        try {
            server = new ServerSocket(PUERTO);
            while (true) {
                Socket connCliente = server.accept();
                new Thread(() -> atenderSocket(connCliente, message)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void atenderSocket(Socket connCliente, String message) {
        try {
            DataOutputStream out = new DataOutputStream(connCliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(connCliente.getInputStream());

            out.writeUTF(SALUDO);

            do {
                message = flujoEntrada.readUTF();
                System.out.println("C: " + message);
                out.writeUTF(message.toUpperCase());
            } while (!message.equalsIgnoreCase(MENSAJE_FIN));
            out.close();
            connCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}