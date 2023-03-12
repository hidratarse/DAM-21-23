package com.example.pmdm_2223.EVA1.practica_2_1;

import android.widget.TextView;

public class Contador {

    int cont;

    public Contador(int cont){
        this.cont=cont;
    }

    public int getCont() {
        return cont;
    }

    public int suma(){
        return cont++;
    }

    public int resta(){
        return cont--;
    }

    public int reset(){
        return cont=0;
    }
}
