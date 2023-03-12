package com.example.examen_pmdm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_pmdm.api.PeliculasRepository;
import com.example.examen_pmdm.data.Pelicula;

import java.util.List;

public class ListadoViewModel extends ViewModel {
    private LiveData<List<Pelicula>> peliculasData;
    private PeliculasRepository peliculasRepository;

    public ListadoViewModel() {
        peliculasRepository = PeliculasRepository.getInstance();
        peliculasData = peliculasRepository.getPeliculasLiveData();
    }

    public void getLista(){
        peliculasRepository.getPeliculas();
    }

    public LiveData<List<Pelicula>> getPeliculasData() {
        return peliculasData;
    }
}
