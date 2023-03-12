package ejemplo.UDP.UDPNormal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    private static int MAX_LENGTH = 65535;
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(8888);
            byte[] buffer = new byte[MAX_LENGTH];

            DatagramPacket p = new DatagramPacket(buffer, MAX_LENGTH);

            while (true) {
                ds.receive(p);
                System.out.println(new String(p.getData(), 0, p.getLength()));
            }         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}