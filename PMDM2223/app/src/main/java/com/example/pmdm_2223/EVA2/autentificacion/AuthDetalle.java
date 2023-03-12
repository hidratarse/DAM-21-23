package com.example.pmdm_2223.EVA2.autentificacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionRequest;
import com.example.pmdm_2223.R;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AuthDetalle extends AppCompatActivity {
    EditText pregunta, fecha;
    Button add, genFecha;
    LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_detalle);

        Intent intent = getIntent();
        String token = intent.getStringExtra("TOKEN");

        pregunta = findViewById(R.id.auth_detalle_pregunta);
        fecha = findViewById(R.id.auth_detalle_fecha);
        add = findViewById(R.id.auth_detalle_añadir);
        genFecha = findViewById(R.id.auth_fecha_actual);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        vm.init();

        add.setOnClickListener(view -> {
            String text = String.valueOf(pregunta.getText());
            String date = String.valueOf(fecha.getText());
            QuestionRequest qr = new QuestionRequest(text, date);
            vm.postQuestion(token, qr);
            finish();
        });

        genFecha.setOnClickListener(view -> {
            String fechaActual = generarFecha();
            fecha.setText(fechaActual);
        });
    }

    private String generarFecha() {
        ZonedDateTime currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            return currentDate.format(formatter);
        }
        return "ERROR";
    }
}