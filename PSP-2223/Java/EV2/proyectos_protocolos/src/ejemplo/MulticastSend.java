package ejemplo;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastSend {
    public static void main(String[] args) {


        try (MulticastSocket ms = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            ms.joinGroup(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
