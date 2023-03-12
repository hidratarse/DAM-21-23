package ejercicios.DNS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLSocketFactory;

public class CambiarIP {

    public static void main(String[] args) {
        String ruta = args[0];
        String IP = extraerMiIp();

        List<String>config = new ArrayList<>();
        extraerConfig(config, ruta);

        String usuario = config.get(0);
        String contraseña = config.get(1);
        String dominio = config.get(2);
        
        cambiarDNS(usuario, contraseña, dominio, IP);
    }

    private static void cambiarDNS(String usuario, String contraseña, String dominio, String IP) {
        String url = "freedns.afraid.org";
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (Socket socket = clientFactory.createSocket(url,443)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String credentials = usuario + ":" + contraseña;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            out.print("GET /nic/update?hostname=" + dominio + "&myip=" + IP + " HTTP/1.1\r\n");
            out.print("Host: " + url + "\r\n");
            out.print("Authorization: Basic " + encodedCredentials + "\r\n");
            out.print("\r\n");
            out.flush();
            
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraerConfig(List<String> config, String ruta) {
        System.out.println("Extrayendo configuración de " + ruta);
        File fichero = new File(ruta);
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String ln;
            while ((ln = br.readLine()) != null) {
                String split[] = ln.split("=");
                config.add(split[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extraerMiIp() {
        String url = "www.cual-es-mi-ip.net";
        String ip = "";
        SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try (Socket socket = clientFactory.createSocket(url, 443)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            out.print("GET / HTTP/1.1\r\n");
            out.print("Host: " + url + "\r\n");
            out.print("\r\n");
            out.flush();

            String s;
            while ((s = in.readLine()) != null) {
                if (s.contains("Tu direcciÃ³n IP es")) {
                    String pattern = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})"; // Patrón para filtrar IPs.

                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(s);

                    if (m.find()) {
                        ip = m.group(1);
                        System.out.println("Mi ip es: " + ip);
                    } else {
                        System.out.println("No IP address found.");
                    }
                }
            }
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }
}