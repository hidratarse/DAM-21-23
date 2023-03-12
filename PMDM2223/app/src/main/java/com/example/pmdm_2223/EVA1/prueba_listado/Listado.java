package com.example.pmdm_2223.EVA1.prueba_listado;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pmdm_2223.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Listado extends AppCompatActivity {

    public static String package_name;

    public ArrayList<Pokemon> pokemons;

    RecyclerView rUser;
    Button add;
    userAdapter adapter;
    AppDatabase db;
    PokemonDAO pokemonDAO;
    private userAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        package_name=getApplicationContext().getPackageName();

        rUser=findViewById(R.id.primerRV);
        add = findViewById(R.id.addPoke);

        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"Pokemon").allowMainThreadQueries().build();

        pokemonDAO=db.pokemonDAO();

        rUser.setHasFixedSize(true);

        rUser.setLayoutManager(new LinearLayoutManager(this));

        pokemons=new ArrayList(pokemonDAO.getAll());

        if (pokemons.size()==0){
            pokemons= new ArrayList<>(
                    Arrays.asList(new Pokemon().generarPokemons(Pokemon.POKEMONS_INICIALES)));
            pokemonDAO.insertList(pokemons);
        }

        ActivityResultLauncher miResultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    Log.d(TAG,"VUELVE CANCELADO");
                    int code = result.getResultCode();
                    switch (code){
                        case RESULT_CANCELED:
                            break;
                        case MainActivity2.CODIGO_SUBIRPOKE:
                                Log.d(TAG,"Se ha recibido un pokemon");
                                Intent data = result.getData();
                                Pokemon nuevoPoke = (Pokemon)data.getSerializableExtra("ENVIO");
                                Log.d("TAG",nuevoPoke.getSprite());
                                pokemons.add(nuevoPoke);
                                pokemonDAO.insertAll(nuevoPoke);
                                adapter=new userAdapter(pokemons,listener);
                                rUser.setAdapter(adapter);
                            break;
                    }
                });

        setOnClickListener(miResultadoLauncher);

        adapter=new userAdapter(pokemons, listener);
        rUser.setAdapter(adapter);

        add.setOnClickListener(view -> {
            Intent intent = new Intent(Listado.this,MainActivity2.class);
            miResultadoLauncher.launch(intent);
        });
    }

    private void setOnClickListener(ActivityResultLauncher miResultadoLauncher){
        listener= (v, position) -> {
            //Iniciar actividad enviando el pokemon a editar, añadir el boton de guardar.
            Pokemon pokeEnvio=pokemons.get(position);
            Intent intent = new Intent(Listado.this,MainActivity2.class);
            intent.putExtra("ENVIO",pokeEnvio);
            miResultadoLauncher.launch(intent);
        };
    }
}