package com.example.pmdm_2223.EVA1.prueba_listado;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pmdm_2223.R;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "Pokemon")
public class Pokemon implements Serializable {

    public static final int POKEMONS_INICIALES = 26;

    private static String []nombres={
            "Bulbasaur", "Ivysaur", "Venusaur", "Charmander",
            "Charmeleon", "Charizard", "Squirtle", "Wartortle",
            "Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle",
            "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot",
            "Rattata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok",
            "Pikachu", "Raichu"
    };

    private static String sprites[]={
            "p1","p2","p3","p4","p5","p6","p7","p8","p9","p10",
            "p11","p12","p13","p14","p15","p16","p17","p18","p19","p20",
            "p21","p22","p23","p24","p25","p26"
    };

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name="sprite")
    private String sprite;

    @PrimaryKey
    @NotNull
    private int numero;

    public Pokemon() {}

    public Pokemon(String nombre, String sprite, int numero) {
        this.nombre = nombre;
        this.sprite=sprite;
        this.numero=numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {return numero;}

    public void setNumero(@NotNull int numero) {
        this.numero = numero;
    }

    public String getSprite(){return sprite;}

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public static Pokemon[] generarPokemons(int n){
        Pokemon[] pokemons =new Pokemon[n];
        for (int i = 0; i < POKEMONS_INICIALES; i++) {
            pokemons[i]=new Pokemon(nombres[i],sprites[i],i);
        }
        return pokemons;
    }
}
