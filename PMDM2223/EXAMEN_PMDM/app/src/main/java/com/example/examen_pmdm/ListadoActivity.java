package com.example.examen_pmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class ListadoActivity extends AppCompatActivity {
    public static final String URL = "1";
    private RecyclerView lista;
    private ListadoAdapter peliculasAdapter;
    private ListadoViewModel vml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e1);

        peliculasAdapter = new ListadoAdapter();

        lista = findViewById(R.id.e1Rv);

        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(peliculasAdapter);

        vml = new ViewModelProvider(this).get(ListadoViewModel.class);

        peliculasAdapter.setClickListener((view, url) -> {
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra(URL,url);
            startActivity(intent);
        });

        vml.getPeliculasData().observe(this, responses -> {
            peliculasAdapter.setResults(responses);
        });

        vml.getLista();
    }
}