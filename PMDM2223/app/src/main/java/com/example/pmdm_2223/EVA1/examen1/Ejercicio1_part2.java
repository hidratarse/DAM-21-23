package com.example.pmdm_2223.EVA1.examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class Ejercicio1_part2 extends AppCompatActivity {

    public static final int CODIGO_RESET=3;

    TextView resultado;
    Button volver, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ejercicio1_2);

        resultado=findViewById(R.id.examenResultado);
        volver=findViewById(R.id.examenEJ1Volver);
        reset=findViewById(R.id.examenEJ1Reset);

        Operacion ope = (Operacion) getIntent().getSerializableExtra(Ejercicio1.ENVIO);

        resultado.setText(String.valueOf(ope.operar()));

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(CODIGO_RESET);
                finish();
            }
        });
    }
}