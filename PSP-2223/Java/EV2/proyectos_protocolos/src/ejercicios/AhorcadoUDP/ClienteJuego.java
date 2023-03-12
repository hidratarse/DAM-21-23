package ejercicios.AhorcadoUDP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteJuego {
    private final static String IP = "192.168.1.127";

    private static Scanner sc = new Scanner(System.in);

    private static int vidas;

    private static boolean palabraAcertada;

    public static void main(String[] args) {
        try {
            Socket con = new Socket(IP, ServidorJuego.PUERTO);
            DataInputStream dataInput = new DataInputStream(con.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());

            System.out.println(dataInput.readUTF());

            // Server pide el nombre del jugador
            String nombre = sc.nextLine();
            dataOut.writeUTF(nombre);

            // Mensaje de bienvenida al juego
            System.out.println(dataInput.readUTF());

            do {
                System.out.println(vidas);
                // Lee imagen
                String imagen = dataInput.readUTF();
                System.out.println(imagen);

                // Lee cadena de inicio
                System.out.println(dataInput.readUTF());

                // Lee letras discponibles
                System.out.println(dataInput.readUTF());

                boolean letraRepetida = true;

                while (letraRepetida) {
                    dataOut.writeUTF(sc.nextLine());

                    letraRepetida = dataInput.readBoolean();
                    if (letraRepetida) {
                        System.out.println("LETRA REPETIDA, INTRODUCE OTRA");
                    }
                }
                vidas = dataInput.readInt();
                palabraAcertada = dataInput.readBoolean();
            } while (vidas!=0 && !palabraAcertada);

            String palabra_original = dataInput.readUTF();

            if (palabraAcertada) {
                System.out.println("CORRECTO!!!!!\nLa palabra era :"+palabra_original);
            } else {
                System.out.println("Terrible, la palabra era: "+palabra_original);
            }

            System.out.println("Cerrando juego");
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