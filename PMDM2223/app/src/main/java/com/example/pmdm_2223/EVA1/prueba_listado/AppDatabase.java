package com.example.pmdm_2223.EVA1.prueba_listado;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonDAO pokemonDAO();
}
