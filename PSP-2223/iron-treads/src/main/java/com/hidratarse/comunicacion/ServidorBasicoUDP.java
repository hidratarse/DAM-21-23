package com.hidratarse.comunicacion;

import java.io.IOException;
import java.net.*;

public class ServidorBasicoUDP {
    private final static int MAX_LENGTH = 65507;
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ingrese el puerto del servidor como argumento.");
            System.exit(0);
        }

        byte[] buffer = new byte[MAX_LENGTH];
        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor UDP iniciado en el puerto " + port + "...");
            DatagramPacket paquete = new DatagramPacket(buffer, MAX_LENGTH);

            while (true) {
                try {
                    socket.receive(paquete);
                    String peticion = recibirPeticion(paquete);
                    InetAddress ipPaquete = paquete.getAddress();
                    int puertoPaquete = paquete.getPort();
                    System.out.println("Mensaje recibido de: " + ipPaquete.toString()
                            + " con contenido :" + peticion);
                    gestionarRespuesta(socket, ipPaquete, puertoPaquete, peticion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static void gestionarRespuesta(DatagramSocket socket, InetAddress ipPaquete, int puertoPaquete,
            String peticion) throws IOException {
        String response = peticion + " XD vale lo que tu digas";
        enviarRespuesta(socket, ipPaquete, puertoPaquete, response);
    }

    private static void enviarRespuesta(DatagramSocket socket, InetAddress ipPaquete, int puertoPaquete,
            String response)
            throws IOException {
        byte mensaje[] = response.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(
                mensaje,
                mensaje.length,
                ipPaquete,
                puertoPaquete);
        socket.send(paqueteEnvio);
    }

    private static String recibirPeticion(DatagramPacket paquete) {
        return new String(paquete.getData(), 0, paquete.getLength());
    }
}