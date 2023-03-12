package com.hidratarse.clase.Ahorcado;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorJuego {
    public final static int PUERTO = 8888;
    private final static String RUTA = "palabras.txt";
    private final static int LINEAS = 8;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            while (true) {
                Socket connCliente = server.accept();
                Jugador jugador = crearJugador(connCliente);
                String palabra = generarPalabra();
                new Thread(
                        new Partida(jugador, palabra, connCliente))
                        .start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generarPalabra() {
        File fichero = new File(RUTA);
        int randomNum = generarRandom();
        String palabra = leerFichero(fichero, randomNum);
        System.out.println("PALABRA GENERADA: "+palabra);
        return palabra;
    }

    private static String leerFichero(File fichero, int randomNum) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String ln;
            String palabra = "";
            int i = 0;
            while ((ln = br.readLine()) != null && i < randomNum) {
                palabra = ln;
                i++;
            }
            return palabra;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private static int generarRandom() {
        return (int)(Math.random()*(LINEAS)+1);
    }

    public static Jugador crearJugador(Socket connCliente) {
        try {
            DataOutputStream out = new DataOutputStream(connCliente.getOutputStream());
            DataInputStream in = new DataInputStream(connCliente.getInputStream());
            out.writeUTF("S: Introduce tu nombre");
            return new Jugador(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}