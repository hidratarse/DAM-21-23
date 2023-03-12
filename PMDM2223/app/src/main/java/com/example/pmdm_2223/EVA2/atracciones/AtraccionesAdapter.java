package com.example.pmdm_2223.EVA2.atracciones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.EVA2.atracciones.data.AtraccionesResponse;
import com.example.pmdm_2223.R;

import java.util.ArrayList;
import java.util.List;

public class AtraccionesAdapter extends RecyclerView.Adapter<AtraccionesAdapter.AtraccionesViewHolder> {
    private List<AtraccionesResponse> data = new ArrayList<>();

    public interface ItemClickListener{
        void onClick(View view, String v);
    }
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AtraccionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_atracciones, parent, false);
        return new AtraccionesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AtraccionesViewHolder holder, int position) {
        holder.getNombre().setText(data.get(position).getNombre());
        holder.getDesc().setText(data.get(position).getDescripcion());
        holder.getOcupantes().setText(String.valueOf(data.get(position).getOcupantes()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setResults(List<AtraccionesResponse> data){
        this.data = data;
        notifyDataSetChanged();
    }
    class AtraccionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre;
        private TextView desc;
        private TextView ocupantes;
        public AtraccionesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.atraccionesNombre);
            desc = itemView.findViewById(R.id.atraccionesDesc);
            ocupantes = itemView.findViewById(R.id.atraccionesOcupantes);

            itemView.setOnClickListener(this);
        }
        public TextView getNombre() {
            return nombre;
        }
        public TextView getDesc() {
            return desc;
        }
        public TextView getOcupantes() {
            return ocupantes;
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, data.get(getAdapterPosition()).getUrl());
            }
        }
    }
}