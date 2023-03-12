package com.example.pmdm_2223.EVA1.testBDD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;


@Entity(tableName = "TestPokemon")
public class TestPokemon {
    @PrimaryKey
    @NotNull
    public String uid;

    @ColumnInfo(name="nombre")
    public String nombre;

    @ColumnInfo(name="tipo")
    public String tipo;

    public TestPokemon(){
        uid= UUID.randomUUID().toString();
    }
}
