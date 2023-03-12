package com.example.pmdm_2223.EVA2.autentificacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionResponse;
import com.example.pmdm_2223.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>{
    private List<QuestionResponse> data = new ArrayList<>();

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auth_viewholder, parent, false);
        return new QuestionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        holder.getPregunta().setText(data.get(position).getQuestionText());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setResults(List<QuestionResponse> data){
        this.data = data;
        notifyDataSetChanged();
    }

    class QuestionsViewHolder extends RecyclerView.ViewHolder {
        private TextView pregunta;
        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            pregunta = itemView.findViewById(R.id.auth_question);
        }
        public TextView getPregunta() {
            return pregunta;
        }
    }
}
