package com.hidratarse.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DAMHttpServer {
    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        String raizServidor = args[1];

        try (ServerSocket ServerSocket = new ServerSocket(puerto);) {
            // pongo el servidor a escuchar
            System.out.println("Servidor iniciado");
            while (true) {
                // acepto cliente
                Socket socket = ServerSocket.accept();
                new Thread(new GestionClientes(socket, raizServidor)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // clase que gestiona el cliente
    public static class GestionClientes implements Runnable {
        private final Socket SOCKET;
        private final String ROOT;

        public GestionClientes(Socket sOCKET, String rOOT) {
            SOCKET = sOCKET;
            ROOT = rOOT;
        }

        @Override
        public void run() {
            try {
                File file = procesarPeticion();
                procesarRespuesta(file);
                SOCKET.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void procesarRespuesta(File file) throws FileNotFoundException, IOException {
            String response;
            if (file.exists()) {
                String content = lecturaHTML(file);
                // construyo la respuesta HTTP
                response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + content.length() + "\r\n"
                        + "\r\n"
                        + content;
            } else {
                // Si el archivo no existe, devuelve un error 404
                response = "HTTP/1.1 404 Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: 0\r\n"
                        + "\r\n";
            }
            PrintWriter out = new PrintWriter(SOCKET.getOutputStream(), true);
            out.println(response);
        }

        private String lecturaHTML(File file) throws FileNotFoundException, IOException {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append("\n");
            }
            fileReader.close();
            String content = contentBuilder.toString();
            return content;
        }

        private File procesarPeticion() throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
            String requestLine = in.readLine();
            
            // separa cualquier espacio en blanco o tabulacion
            String[] tokens = requestLine.split("\\s");
            String filePath = tokens[1];
            System.out.println(ROOT + filePath);

            return new File(ROOT + filePath);
        }
    }
}