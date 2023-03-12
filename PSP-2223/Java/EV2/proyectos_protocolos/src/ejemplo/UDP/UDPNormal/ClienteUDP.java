package ejemplo.UDP.UDPNormal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();

            byte buffer[] = "Mondongo ".getBytes();

                DatagramPacket p = new DatagramPacket(
                    buffer, 
                    buffer.length,
                    InetAddress.getLocalHost(),
                    8888
                );

                while (true) {
                    ds.send(p);
                }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}