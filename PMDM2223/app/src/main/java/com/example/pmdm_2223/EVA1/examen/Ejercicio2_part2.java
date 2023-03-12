package com.example.pmdm_2223.EVA1.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class Ejercicio2_part2 extends AppCompatActivity {

    TextView numeroP, precioTot, listadoP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ejercicio2_part2);

        numeroP=findViewById(R.id.numProductos);
        precioTot=findViewById(R.id.precioTotal);
        listadoP=findViewById(R.id.listarProductos);

        numeroP.setText(Ejercicio2.listaTotal.size()+" PRODUCTOS");

        precioTot.setText(String.valueOf(obtenerTotal()));

        listadoP.setText(obtenerNombres());
    }

    private String obtenerNombres() {
        String todos=" ";
        for (int i = 0; i < Ejercicio2.listaTotal.size(); i++) {
            todos+=Ejercicio2.listaTotal.get(i).getNombre()+", ";
        }
        return todos;
    }

    public int obtenerTotal(){
        int total=0;
        for (int i = 0; i < Ejercicio2.listaTotal.size(); i++) {
            total+=Ejercicio2.listaTotal.get(i).getPrecio();
        }
        return total;
    }
}