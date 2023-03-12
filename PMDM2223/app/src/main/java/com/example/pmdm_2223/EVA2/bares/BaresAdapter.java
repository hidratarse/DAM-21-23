package com.example.pmdm_2223.EVA2.bares;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.EVA2.bares.data.BaresResponse;
import com.example.pmdm_2223.R;

import java.util.ArrayList;
import java.util.List;

public class BaresAdapter extends RecyclerView.Adapter<BaresAdapter.BaresViewHolder>{
    private List<BaresResponse> data = new ArrayList<>();

    @NonNull
    @Override
    public BaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_bares, parent, false);
        return new BaresViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaresViewHolder holder, int position) {
        holder.getNombre().setText(data.get(position).getNombre());
        holder.getApertura().setText(data.get(position).getApertura());
        holder.getDesc().setText(data.get(position).getDescripcion());
        holder.getCierre().setText(data.get(position).getCierre());
        holder.getEstrellas().setText(String.valueOf(data.get(position).getEstrellas()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setResults(List<BaresResponse> data){
        this.data = data;
        notifyDataSetChanged();
    }

    class BaresViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView desc;
        private TextView cierre;
        private TextView apertura;
        private TextView estrellas;
        public BaresViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.baresNombre);
            desc = itemView.findViewById(R.id.baresDesc);
            cierre = itemView.findViewById(R.id.baresCierre);
            apertura = itemView.findViewById(R.id.baresApertura);
            estrellas = itemView.findViewById(R.id.baresEstrellas);
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getDesc() {
            return desc;
        }

        public TextView getCierre() {
            return cierre;
        }

        public TextView getApertura() {
            return apertura;
        }

        public TextView getEstrellas() {
            return estrellas;
        }
    }
}
