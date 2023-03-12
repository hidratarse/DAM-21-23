package com.example.examen_pmdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_pmdm.data.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ListadoAdapter extends RecyclerView.Adapter<ListadoAdapter.AdapterHolder> {
    private List<Pelicula> data = new ArrayList<>();

    public interface ItemClickListener {
        void onClick(View view, String v);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_e1, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        String desc = filtrarDesc(data.get(position).getDescripcion());
        holder.getTitulo().setText(data.get(position).getNombre());
        holder.getDesc().setText(desc);
        holder.getEstrella().setText(data.get(position).getEstrellas());
    }

    public static String filtrarDesc(String texto) {
        StringBuilder resultado = new StringBuilder();
        String[] palabras = texto.split(" \\s+");
        for (int i = 0; i < 5 && i < palabras.length; i++) {
            resultado.append(palabras[i] + " ");
        }
        return resultado.toString();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setResults(List<Pelicula> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titulo;
        private TextView desc;
        private TextView estrella;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.peliTitulo);
            desc = itemView.findViewById(R.id.peliDesc);
            estrella = itemView.findViewById(R.id.listadoEstrella);
            itemView.setOnClickListener(this);
        }

        public TextView getTitulo() {
            return titulo;
        }

        public TextView getDesc() {
            return desc;
        }

        public TextView getEstrella() {
            return estrella;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v, data.get(getAdapterPosition()).getUrl());
            }
        }
    }
}

