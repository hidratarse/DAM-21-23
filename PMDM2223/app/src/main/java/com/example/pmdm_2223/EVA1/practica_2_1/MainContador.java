package com.example.pmdm_2223.EVA1.practica_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class MainContador extends AppCompatActivity {

    Button sumador;
    Button restador;
    Button reseteador;
    TextView contador;
    Contador i = new Contador(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eva1_2_1);

        sumador=findViewById(R.id.botonsuma);
        restador=findViewById(R.id.botonresta);
        reseteador=findViewById(R.id.botonreset);
        contador=findViewById(R.id.contador);

        sumador.setOnClickListener(new Controlador(i, contador, sumador));
        restador.setOnClickListener(new Controlador(i, contador, restador));
        reseteador.setOnClickListener(new Controlador(i, contador,reseteador));
    }
}