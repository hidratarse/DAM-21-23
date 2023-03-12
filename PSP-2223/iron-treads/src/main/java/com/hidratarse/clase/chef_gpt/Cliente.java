package com.hidratarse.clase.chef_gpt;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try (Socket con = new Socket("192.168.1.127", 12345)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
