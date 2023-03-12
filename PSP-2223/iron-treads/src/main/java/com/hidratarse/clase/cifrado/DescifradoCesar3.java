package com.hidratarse.clase.cifrado;

public class DescifradoCesar3 {
    private final static int DESPLAZAMIENTO = 3;
    private final static int a = 97;
    private final static int A = 65;
    private final static int RANGO = 26;
    public static void main(String[] args) {
        if (args.length == 1) {
            String textoCifrado = args[0];
            String textoDescifrado = "";
            for (int i = 0; i < textoCifrado.length(); i++) {
                char c = textoCifrado.charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        c = (char) ((c - A - DESPLAZAMIENTO + RANGO) % RANGO + A);
                    } else {
                        c = (char) ((c - a - DESPLAZAMIENTO + RANGO) % RANGO + a);
                    }
                }
                textoDescifrado += c;
            }
            System.out.println(textoDescifrado);
        } else {
            System.out.println("Introduce la palabra a cifrar");
        }
    }
}
