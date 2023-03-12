package com.hidratarse.comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientePrimosTCP {
    public static void main(String[] args) {
        String message = "";
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        try(Socket con = new Socket(ip, port);) {
            
            DataInputStream dataInput = new DataInputStream(con.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());

            Scanner sc = new Scanner(System.in);

            do {
                message = sc.nextLine();
                dataOut.writeUTF(message);
                System.out.println("S: "+dataInput.readUTF());
            } while (!message.equals("0"));

            sc.close();
            dataInput.close();
            dataOut.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}