package com.example.pmdm_2223.EVA2.n_primos_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class N_Primos_Mvvm extends AppCompatActivity {

    EditText rango1;
    EditText rango2;
    TextView result;
    Button go;
    PrimoViewModel viewmodel;
    int n1, n2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nprimos_mvvm);

        rango1=findViewById(R.id.nprimo1);
        rango2=findViewById(R.id.nprimo2);
        result = findViewById(R.id.nprimo_result);
        go = findViewById(R.id.n_primo_go);

        go.setOnClickListener(view -> {
            n1= Integer.parseInt(rango1.getText().toString());
            n2= Integer.parseInt(rango2.getText().toString());
            viewmodel.generaNPrimos(n1,n2);
            result.setText("CARGANDO");
        });

        viewmodel = new ViewModelProvider(this).get(PrimoViewModel.class);
        viewmodel.getDato().observe(this, primoModel ->{
            result.setText(String.valueOf(primoModel.getNPrimos()));
        });
    }
}