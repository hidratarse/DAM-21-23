package com.example.pmdm_2223.EVA1.testBDD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pmdm_2223.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add;
    Button recargar;
    EditText nombre;
    EditText tipo;
    TextView datos;
    AppDatabase db;
    TestPokemonDAO testPokemonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.testADD);
        recargar = findViewById(R.id.testRefresh);
        nombre = findViewById(R.id.testPokemon);
        tipo = findViewById(R.id.testTipo);
        datos = findViewById(R.id.ut03idAnimalDatos);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"TestPokemon").allowMainThreadQueries().build();
        testPokemonDAO=db.testPokemonDAO();

        recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TestPokemon> testPokemons = testPokemonDAO.getAll();
                String sData="";
                for (TestPokemon p:testPokemons) {
                    sData+=p.nombre+" - "+p.tipo+"\n";
                }
                datos.setText(sData);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestPokemon p = new TestPokemon();
                p.nombre = nombre.getText().toString();
                p.tipo=tipo.getText().toString();
                testPokemonDAO.insertAll(p);
            }
        });
    }
}