package com.example.pmdm_2223.EVA1.lanzador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pmdm_2223.R;

public class Lanzador extends AppCompatActivity {
    static String nome="";
    Button lanzador;
    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzador);

        lanzador= findViewById(R.id.ut03lanzador);
        nombre=findViewById(R.id.tUnombre);

        lanzador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzar();
            }
        });
    }

    private
    void lanzar(){
        Intent intento = new Intent(this, ut03Receptora.class);
        intento.putExtra(nome, nombre.getText().toString());
        startActivity(intento);
    }
}