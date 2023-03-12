package ejercicios.Ahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Partida implements Runnable {

    private final int NUM_FALLOS = 6;

    private StringBuilder letrasDisponibles;

    private String palabraOriginal;

    private StringBuilder palabraJugador;

    public int vidas;

    private Jugador jugador;

    private Socket conexion;

    private DataInputStream inTCP;

    private DataOutputStream outTCP;

    public boolean palabraAcertada = false;

    public Partida(Jugador jugador, String palabraOriginal, Socket conexion) {
        this.jugador = jugador;
        this.palabraOriginal = palabraOriginal.toUpperCase();
        this.vidas = NUM_FALLOS;
        this.palabraJugador = new StringBuilder("______");
        this.letrasDisponibles = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.conexion = conexion;
        try {
            outTCP = new DataOutputStream(conexion.getOutputStream());
            inTCP = new DataInputStream(conexion.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Aquí añadir código de partida
        System.out.println("JUGANDO CON: " + jugador.getNombre());
        try {
            outTCP.writeUTF("S: Bienvenido " + jugador.getNombre() + " tienes " + vidas + " vidas");
            do {
                System.out.println(palabraJugador);
                System.out.println("Vidas restantes " + vidas);
                mostrarImagen();
                mostrarPalabraJugador();
                mostrarletrasDisponibles();
                jugadaUsuario();
            } while (vidas != 0 && !palabraAcertada);
            outTCP.writeUTF(palabraOriginal);
            conexion.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jugadaUsuario() throws IOException {
        String letraUsu = validacionLetra();
        boolean contiene = false;
        for (int i = 0; i < palabraJugador.length(); i++) {
            if (letraUsu.equals(palabraOriginal.toString().charAt(i) + "")) {
                palabraJugador.replace(i, i + 1, letraUsu);
                contiene = true;
            }
        }
        if (!contiene)
            vidas--;
        quitarLetra(letraUsu);
        outTCP.writeInt(vidas);
        if (palabraJugador.toString().equals(palabraOriginal)) {
            outTCP.writeBoolean(true);
            palabraAcertada = true;
        }else
            outTCP.writeBoolean(false);
    }

    private void quitarLetra(String letraUsu) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < letrasDisponibles.length()) {
            if (letraUsu.equalsIgnoreCase(letrasDisponibles.charAt(i) + "")) {
                letrasDisponibles.replace(i, i + 1, " ");
                System.out.println(letrasDisponibles);
                encontrado = true;
            }
            i++;
        }
    }

    private String validacionLetra() throws IOException {
        String letraUsu;
        boolean letraRepetida;
        do {
            System.out.println("buscando");
            letraRepetida = true;
            int i = 0;
            letraUsu = inTCP.readUTF().toUpperCase();
            System.out.println("Recibido " + letraUsu);
            while (letraRepetida && i < letrasDisponibles.length()) {
                System.out.println(letrasDisponibles.charAt(i));
                if (letraUsu.equalsIgnoreCase(letrasDisponibles.charAt(i) + "")) {
                    System.out.println("Letra se encuentra disponible");
                    letraRepetida = false;
                }
                i++;
            }
            outTCP.writeBoolean(letraRepetida);
            System.out.println(letraRepetida);
        } while (letraRepetida);
        return letraUsu;
    }

    private void mostrarletrasDisponibles() throws IOException {
        StringBuilder lineaMuestra = new StringBuilder();
        for (int i = 0; i < letrasDisponibles.length(); i++) {
            lineaMuestra.append(letrasDisponibles.charAt(i) + " | ");
        }
        outTCP.writeUTF(lineaMuestra.toString());
    }

    private void mostrarPalabraJugador() throws IOException {
        outTCP.writeUTF("\t" + palabraJugador.toString());
    }

    private void mostrarImagen() throws IOException {
        String[] imagenes = {
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t   \t|\n" +
                        "\t   \t|\n" +
                        "\t    \t|\n" +
                        "\t========|",
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t | \t|\n" +
                        "\t   \t|\n" +
                        "\t    \t|\n" +
                        "\t========|",
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t |\\\t|\n" +
                        "\t   \t|\n" +
                        "\t    \t|\n" +
                        "\t========|",
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t/|\\\t|\n" +
                        "\t   \t|\n" +
                        "\t    \t|\n" +
                        "\t========|",
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t/|\\\t|\n" +
                        "\t/  \t|\n" +
                        "\t    \t|\n" +
                        "\t========|",
                "\t --------\n" +
                        "\t |\t|\n" +
                        "\t O\t|\n" +
                        "\t/|\\\t|\n" +
                        "\t/ \\\t|\n" +
                        "\t    \t|\n" +
                        "\t========|"
        };
        System.out.println("Enviando imagen");
        outTCP.writeUTF(imagenes[vidas - 1]);
    }
}