package com.hidratarse.comunicacion;

import java.io.IOException;
import java.net.*;

public class ServidorUDPBroadcast {
    private final static int MAX_LENGTH = 65507;
    private final static String SUFIJO_BROADCAST = ".255";
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ingrese el puerto del servidor como argumento.");
            System.exit(0);
        }

        byte[] buffer = new byte[MAX_LENGTH];
        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor UDP broadcast iniciado en el puerto " + port + "...");
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
        InetAddress ipBroadcast = getBroadcast(ipPaquete);
        System.out.println("Enviando a: "+ipBroadcast.getHostAddress());
        enviarRespuesta(socket, ipBroadcast, puertoPaquete, peticion);
    }

    private static InetAddress getBroadcast(InetAddress ipPaquete) {
        String ip = ipPaquete.getHostAddress();
        String primerosTres = ip.substring(0, ip.lastIndexOf(".")); // 192.168.1
        ip = primerosTres + SUFIJO_BROADCAST;
        InetAddress ipBroadcast;
        try {
            ipBroadcast = InetAddress.getByName(ip);
            return ipBroadcast;
        } catch (UnknownHostException e) {
            return null;
        }
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