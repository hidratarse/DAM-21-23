package com.example.pmdm_2223.EVA1.testBDD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
    public interface TestPokemonDAO {
        @Query("SELECT * FROM TestPokemon")
        List<TestPokemon> getAll();

        @Query("SELECT * FROM TestPokemon WHERE uid IN (:testPokemonIds)")
        List<TestPokemon> loadAllByIds(int[] testPokemonIds);

        @Query("SELECT * FROM TestPokemon WHERE nombre LIKE :nombre LIMIT 1")
        TestPokemon findByName(String nombre);

        @Insert
        void insertAll(TestPokemon... users);

        @Delete
        void delete(TestPokemon user);
}
