package com.hidratarse.clase.Cliente_Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    private final static String IP = "192.168.1.127";

    public static void main(String[] args) {
        String message = "";
        try {
            Socket con = new Socket(IP, Server.PUERTO);
            DataInputStream dataInput = new DataInputStream(con.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());

            System.out.println("S: "+dataInput.readUTF());

            Scanner sc = new Scanner(System.in);

            do {
                message = sc.nextLine();
                dataOut.writeUTF(message);
                System.out.println("S: "+dataInput.readUTF());
            } while (!message.equalsIgnoreCase(Server.MENSAJE_FIN));

            sc.close();
            dataInput.close();
            dataOut.close();
            con.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}