package com.example.pmdm_2223.EVA1.prueba_listado;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PokemonDAO {

    @Query("SELECT * FROM Pokemon")
    List<Pokemon> getAll();

    @Query("SELECT * FROM Pokemon WHERE numero in(:pokemonNumbers)")
    List<Pokemon> loadAllByNumber(int[] pokemonNumbers);

    @Query("SELECT * FROM Pokemon WHERE numero = :number")
    Pokemon findByNumber(int number);

    @Query("SELECT * FROM Pokemon WHERE nombre LIKE :nombre LIMIT 1")
    Pokemon findByName(String nombre);

    @Insert
    void insertAll(Pokemon... pokemons);

    @Insert
    void insertList(ArrayList<Pokemon> pokemons);

    @Delete
    void delete(Pokemon pokemon);
}
