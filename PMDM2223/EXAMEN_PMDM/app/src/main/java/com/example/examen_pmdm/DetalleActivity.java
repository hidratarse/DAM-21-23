package com.example.examen_pmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.examen_pmdm.data.Actor;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView desc;
    private TextView estrellas;
    private TextView actores;
    private DetalleViewModel vml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e1detalle);

        titulo = findViewById(R.id.detalleTitulo);
        desc = findViewById(R.id.detalleDesc);
        estrellas = findViewById(R.id.detalleEstrellas);
        actores = findViewById(R.id.detalleLista);

        Intent intent = getIntent();
        String url = intent.getStringExtra(ListadoActivity.URL);

        vml = new ViewModelProvider(this).get(DetalleViewModel.class);

        vml.getDetalleData().observe(this, response -> {
            titulo.setText(response.getNombre());
            desc.setText(response.getDescripcion());
            estrellas.setText(response.getEstrellas());
            String textoActores = getActores(response.getActores());
            actores.setText(textoActores);
        });

        vml.getDetalle(url);
    }

    private String getActores(List<Actor> listaActores) {
        StringBuilder actores = new StringBuilder();
        for (Actor actor : listaActores) {
            actores.append(actor.getNombre());
            actores.append("\n");
        }
        return actores.toString();
    }
}