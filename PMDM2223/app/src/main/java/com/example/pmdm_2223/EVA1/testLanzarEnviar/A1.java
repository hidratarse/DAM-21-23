package com.example.pmdm_2223.EVA1.testLanzarEnviar;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pmdm_2223.R;

public class A1 extends AppCompatActivity {
    public static final String MESSAGE_NOMBRE ="nombre???";
    EditText escribirnombre;
    Button enviar, salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        escribirnombre = findViewById(R.id.escribirNombre);
        enviar = findViewById(R.id.enviarA1);
        salir = findViewById(R.id.bSalir);

        ActivityResultLauncher someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    Log.d(TAG, "Vuelve cancelado");
                    int code = result.getResultCode();
                    switch (code) {
                        case RESULT_CANCELED:
                            break;
                        case A2.CODIGO_VUELTALIMPIAR:
                            Log.d(TAG, "limpiar texto");
                            escribirnombre.setText("");
                            break;
                        case A2.CODIGO_VUELTA_TEXTO:
                            Log.d(TAG, "Vuelve con código, buscar intent");
                            Intent intent = result.getData();
                            String mensaje = intent.getStringExtra(A2.MSG);
                            escribirnombre.setText(mensaje);
                            break;
                        case A2.CODIGO_REVERSO:
                            Log.d(TAG, "Vuelve con el texto reverso");
                            Intent intent1 = result.getData();
                            String mensaje2 = intent1.getStringExtra(A2.MSG);
                            escribirnombre.setText(mensaje2);
                            break;
                    }
                }
        );

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A1.this, A2.class);
                String nombre = escribirnombre.getText().toString();
                intent.putExtra(MESSAGE_NOMBRE, nombre);
                someActivityResultLauncher.launch(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}