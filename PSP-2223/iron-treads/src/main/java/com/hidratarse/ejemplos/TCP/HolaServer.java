package com.hidratarse.ejemplos.TCP;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HolaServer {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(8888);
            Socket connCliente = server.accept();

            BufferedOutputStream out = new BufferedOutputStream(connCliente.getOutputStream());

            out.write("Bienvenido a MONDONGO".getBytes());

            out.close();
            connCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
