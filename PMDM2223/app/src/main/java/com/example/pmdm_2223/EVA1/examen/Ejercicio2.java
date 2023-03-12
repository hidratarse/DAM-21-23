package com.example.pmdm_2223.EVA1.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pmdm_2223.R;

import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorProductos adapter;
    Button total;

    public ArrayList<Producto>lista;
    public static ArrayList<Producto> listaTotal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ejercicio2_1);

        recyclerView=findViewById(R.id.exameReciclado);
        total=findViewById(R.id.examenTotal);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lista= new ArrayList<>(Producto.generador());

        adapter=new AdaptadorProductos(lista);
        recyclerView.setAdapter(adapter);



        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ejercicio2.this,Ejercicio2_part2.class );
                startActivity(intent);
            }
        });
    }
}