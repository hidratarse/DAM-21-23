package com.example.pmdm_2223.EVA1.lanzador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdm_2223.R;



public class ut03Receptora extends AppCompatActivity {
    TextView mensaje;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ut03_receptora);
        mensaje=findViewById(R.id.Receptore);

        Intent intent= getIntent();
        String message = intent.getStringExtra((Lanzador.nome));

        mensaje.setText(message);
    }
}
