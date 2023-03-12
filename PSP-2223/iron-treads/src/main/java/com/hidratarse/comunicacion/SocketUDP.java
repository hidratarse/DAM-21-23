package com.hidratarse.comunicacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

//CLASE CREADA PARA FACILITAR ENVIAR Y RECIBIR EN UN SOCKET UDP
public class SocketUDP {
    private DatagramSocket socket;
    private final int MAX_LENGTH = 65507;
    private final InetAddress IP_LOCAL;
    private final int PUERTO_LOCAL;

    public SocketUDP(int port) throws SocketException {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("PUERTO USADO, ASIGNANDO OTRO");
            socket = new DatagramSocket();
        }
        IP_LOCAL = socket.getLocalAddress();
        PUERTO_LOCAL = socket.getLocalPort();
    }

    public void enviarMensaje(String message, String ip, int port) throws IOException {
        ip = ip.replace("/", "");
        byte[] buffer = message.getBytes();
        DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), port);
        this.socket.send(p);
    }

    public String recibirMensaje() throws IOException {
        byte[] buffer = new byte[MAX_LENGTH];
        DatagramPacket p = new DatagramPacket(buffer, MAX_LENGTH);
        this.socket.receive(p);
        return new String(p.getData(), 0, p.getLength());
    }

    public void cerrar() {
        this.socket.close();
    }

    public InetAddress getIPLocal() {
        return IP_LOCAL;
    }

    public int getPuertoLocal() {
        return PUERTO_LOCAL;
    }
}