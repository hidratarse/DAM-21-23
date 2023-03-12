package com.example.pmdm_2223.EVA2.bares;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.R;

public class BaresActivity extends AppCompatActivity {
    private RecyclerView lista;
    private EditText param;
    private Button filtrar;
    private BaresAdapter adapter;
    private BaresViewModel vml;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bares);
        adapter = new BaresAdapter();

        lista = findViewById(R.id.baresLista);
        param = findViewById(R.id.baresFiltro);
        filtrar = findViewById(R.id.bareBoton);
        progressBar = findViewById(R.id.baresProgress);
        progressBar.setVisibility(View.INVISIBLE);

        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);

        vml = new ViewModelProvider(this).get(BaresViewModel.class);
        vml.init();

        vml.getBaresResponse().observe(this, (dato) -> {
            adapter.setResults(dato);
            filtrar.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        });

        filtrar.setOnClickListener(view -> {
            filtrar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            int estrellas = Integer.parseInt(String.valueOf(param.getText()));
            vml.filtradoBares(estrellas);
        });

        vml.getBares();
    }
}