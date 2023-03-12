package com.example.pmdm_2223.EVA1.testBDD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TestPokemon.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TestPokemonDAO testPokemonDAO();
}
