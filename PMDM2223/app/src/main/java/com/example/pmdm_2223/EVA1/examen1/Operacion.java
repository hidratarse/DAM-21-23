package com.example.pmdm_2223.EVA1.examen1;

import java.io.Serializable;

public class Operacion implements Serializable {

    private int numero1;

    private int numero2;

    private String operacion;

    public Operacion(int numero1, int numero2, String operacion) {
        this.numero1 = numero1;
        this.numero2=numero2;
        this.operacion=operacion;
    }

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int operar(){
        switch (operacion){
            case Ejercicio1.SUMA:
                return numero1+numero2;
            case Ejercicio1.RESTA:
                return numero1-numero2;
            case Ejercicio1.MULTI:
                return numero1*numero2;
            case Ejercicio1.DIV:
                return numero1/numero2;
        }
        return 0;
    }
}
