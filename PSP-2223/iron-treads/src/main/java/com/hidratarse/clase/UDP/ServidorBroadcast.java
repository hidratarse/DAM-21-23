package com.hidratarse.clase.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorBroadcast {
    private final static int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        byte[] buffer = new byte[MAX_LENGTH];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("SERVIDOR: ENCENDIDO EN PUERTO: " + socket.getLocalPort());
            DatagramPacket paquete = new DatagramPacket(buffer, MAX_LENGTH);
            while (true) {
                socket.receive(paquete);
                String ip = paquete.getAddress().toString();
                System.out.println("IP CONECTADA "+ip);
                int puerto = paquete.getPort();
                String ipBroadcast = transformaABroadcast(ip);
                System.out.println("IP BROADCAST "+ipBroadcast + "\nPUERTO " + puerto);
                String msg = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("MENSAJE: "+msg);
                byte mensaje[] = msg.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(
                        mensaje,
                        mensaje.length,
                        InetAddress.getByName(ipBroadcast),
                        puerto);
                socket.send(paqueteEnvio);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String transformaABroadcast(String ip) {
        String raw = ip.substring(1);
        String primerosTres = raw.substring(0, ip.lastIndexOf(".")); // 192.168.1
        ip = primerosTres + "255";
        return ip;
    }
}
