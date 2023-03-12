package com.hidratarse.comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPrimosTCP {
    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        System.out.println("Servidor encendido en el puerto: " + puerto);
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket con;

    public ManejadorCliente(Socket con) {
        this.con = con;
    }

    public void run() {
        try (DataInputStream dataInput = new DataInputStream(con.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());) {
    
            String message = "";
            do {
                if (dataInput.available() > 0) { // Comprobar si hay datos disponibles
                    message = dataInput.readUTF();
                    System.out.println("C: " + message);
                    String response = procesarMensaje(message);
                    dataOut.writeUTF(response);
                }
            } while (!message.equals("0"));
    
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String procesarMensaje(String message) {
        String response = "";
        try {
            int num = Integer.parseInt(message);
            Boolean esPrimo = comprobarPrimo(num);
            if (esPrimo) {
                response = num + " es primo mi rey";
            } else {
                response = num + " no es primo tontito";
            }
        } catch (NumberFormatException e2) {
            response = "eso ni siquiera es un numero";
        }
        return response;
    }

    private static Boolean comprobarPrimo(int num) {
        if (num <= 1) {
            // Los números menores o iguales a 1 no se consideran primos
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                // Si encontramos un divisor del número, no es primo
                return false;
            }
        }
        // Si no encontramos ningún divisor, el número es primo
        return true;
    }
}