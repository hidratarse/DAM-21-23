package com.example.pmdm_2223.EVA1.examen;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.R;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder>{

    private ArrayList<Producto> datos;

    public AdaptadorProductos(ArrayList<Producto> dataSet){
        datos=dataSet;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView nombre;

        private final TextView precio;

        public ViewHolder(View v) {
            super(v);
            nombre=v.findViewById(R.id.nombreProducto);
            precio=v.findViewById(R.id.precioProducto);
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getPrecio() {
            return precio;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.examen_vh,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombreP = datos.get(position).getNombre();
        int precioP = datos.get(position).getPrecio();
        holder.getNombre().setText(nombreP);
        holder.getPrecio().setText(String.valueOf(precioP));

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ejercicio2.listaTotal.add(new Producto(nombreP,precioP));
            }
        });

        holder.precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ejercicio2.listaTotal.add(new Producto(nombreP,precioP));
            }
        });


    }



    @Override
    public int getItemCount() {
        return datos.size();
    }
}