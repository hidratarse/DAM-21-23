package com.example.pmdm_2223.EVA2.atracciones;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.R;

public class AtraccionesActivity extends AppCompatActivity {
    public static final String ID_DETALLE="ID";
    private RecyclerView lista;
    private AtraccionesAdapter adapter;
    public AtraccionesViewModel vml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atracciones);

        adapter = new AtraccionesAdapter();

        lista = findViewById(R.id.atraccionesRV);

        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);

        vml = new ViewModelProvider(this).get(AtraccionesViewModel.class);

        vml.setCampoPrueba("XDDDDDDDDDDDDDDDDDD");

        vml.getListaData().observe(this, (dato) -> {
            adapter.setResults(dato);
        });

        adapter.setClickListener((view, v) -> {
            String id = getId(v);
            Intent intent = new Intent(this, ComentariosActivity.class);
            intent.putExtra(ID_DETALLE, id);
            startActivity(intent);
        });

        vml.getAtracciones();
    }
    private String getId(String url) {
        String[] partes = url.split("/");
        String id = partes[partes.length - 1];
        return id;
    }
}