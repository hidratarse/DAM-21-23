package com.example.pmdm_2223.EVA2.atracciones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.R;

public class ComentariosActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView dec;
    private TextView ocupantes;
    private RecyclerView lista;
    private ComentariosAdapter adapter;
    private AtraccionesViewModel vml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        nombre = findViewById(R.id.comentarioNombre);
        dec = findViewById(R.id.comentarioDesc);
        ocupantes = findViewById(R.id.comentarioOcupantes);
        lista = findViewById(R.id.comentariosRV);

        adapter = new ComentariosAdapter();
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);

        vml = new ViewModelProvider(this).get(AtraccionesViewModel.class);
        Log.d("KEK",vml.getCampoPrueba());
        Intent intent = getIntent();
        String id = intent.getStringExtra(AtraccionesActivity.ID_DETALLE);

        vml.getDetalleData().observe(this, response -> {
            adapter.setResults(response.getComentarios());
        });

        vml.getDetalle(id);
    }
}