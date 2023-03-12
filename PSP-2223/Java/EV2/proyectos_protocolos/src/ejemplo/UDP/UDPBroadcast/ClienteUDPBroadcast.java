package ejemplo.UDP.UDPBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDPBroadcast {
    public static void main(String[] args) {
        try {
            String ip = "192.168.20.255"; // ip de broadcast 255.255.255.255
            DatagramSocket ds = new DatagramSocket();

            byte buffer[] = "mondongo".getBytes();

            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    12345);

            while (true) {
                ds.send(p);
                Thread.sleep(1000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}