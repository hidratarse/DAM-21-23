package com.hidratarse.clase.cifrado;

public class CifradoCesar3 {
    private final static int DESPLAZAMIENTO = 3;
    private final static int a = 97;
    private final static int A = 65;
    private final static int RANGO = 26;
    public static void main(String[] args) {
        String cifrado = "";
        if (args.length==1) {
            System.out.println("Entra: "+args[0]);
            for (int i = 0; i < args[0].length(); i++) {
                char c = args[0].charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isUpperCase(c)) {
                        c = (char) ((c - A + DESPLAZAMIENTO) % RANGO + A);
                    } else {
                        c = (char) ((c - a + DESPLAZAMIENTO) % RANGO + a);
                    }
                }
                cifrado+=c;
            }
            System.out.println(cifrado);
        }else{
            System.out.println("Introduce la palabra a cifrar");
        }
    }
}
