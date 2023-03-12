package com.example.pmdm_2223.EVA2.ejemplo_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.pmdm_2223.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class calculadora_api extends AppCompatActivity {
    TextView label;
    EditText n1, n2;
    RadioButton suma,resta,div,multi;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_suma_api);

        calcular = findViewById(R.id.id_api_boton);
        label = findViewById(R.id.id_api_label);
        suma = findViewById(R.id.id_api_suma);
        resta = findViewById(R.id.id_api_resta);
        multi = findViewById(R.id.id_api_multiplicacion);
        div = findViewById(R.id.id_api_division);
        n1 = findViewById(R.id.id_api_n1);
        n2 = findViewById(R.id.id_api_n2);

        calcular.setOnClickListener(view ->{
            int num1 = Integer.parseInt(n1.getText().toString());
            int num2 = Integer.parseInt(n2.getText().toString());
            if (suma.isChecked()){
                sumar(num1, num2);
            }
            if (resta.isChecked()){
                restar(num1, num2);
            }
            if (multi.isChecked()){
                multiplicar(num1, num2);
            }
            if (div.isChecked()){
                dividir(num1, num2);
            }
        });
    }

    private void dividir(int num1, int num2) {
        Call<Resultado> llamada = MatesAPI.getInstance().division(num1,num2);

        llamada.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                label.setText(response.body().resultado);
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                label.setText("ERROR");
            }
        });
    }

    private void multiplicar(int num1, int num2) {
        Call<Resultado> llamada = MatesAPI.getInstance().multiplicacion(num1,num2);

        llamada.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                label.setText(response.body().resultado);
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                label.setText("ERROR");
            }
        });
    }

    private void restar(int num1, int num2) {
        Call<Resultado> llamada = MatesAPI.getInstance().resta(num1,num2);

        llamada.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                label.setText(response.body().resultado);
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                label.setText("ERROR");
            }
        });
    }

    private void sumar(int num1, int num2){
        Call<Resultado> llamada = MatesAPI.getInstance().suma(num1,num2);

        llamada.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                label.setText(response.body().resultado);
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                label.setText("ERROR");
            }
        });
    }
}