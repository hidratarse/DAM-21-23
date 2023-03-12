package ejercicios.chef_gpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class DynamicIPUpdaterServer {
    private static final int PORT = 12345;
    private static final String USERNAME = "[USERNAME]";
    private static final String PASSWORD = "[PASSWORD]";
    private static final String DOMAIN = "[DOMAIN]";
    private static final String UPDATE_URL = "http://" + USERNAME + ":" + PASSWORD +
            "@freedns.afraid.org/nic/update?hostname=" + DOMAIN + "&myip=";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión establecida con un cliente");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String clientAddress = reader.readLine();
                System.out.println("Dirección IP actualizada: " + clientAddress);
                updateIPAddress(clientAddress);
            } catch (IOException e) {
                System.err.println("Error al leer la dirección IP del cliente: " + e.getMessage());
            }
        }

        private void updateIPAddress(String clientAddress) {
            try {
                URL url = new URL(UPDATE_URL + clientAddress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Dirección IP actualizada exitosamente");
                } else {
                    System.err.println("Error al actualizar la dirección IP: " + responseCode);
                }
            } catch (IOException e) {
                System.err.println("Error al actualizar la dirección IP: " + e.getMessage());
            }
        }
    }
}