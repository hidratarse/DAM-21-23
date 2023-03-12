package com.example.pmdm_2223.EVA1.testLanzarEnviar;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class A2 extends AppCompatActivity {

    public static final int CODIGO_VUELTALIMPIAR =2;
    public static final int CODIGO_VUELTA_TEXTO = 3;
    public static final int CODIGO_REVERSO=4;
    public static final String MSG = "texto";
    public static final String ENVIOTEXT0 = "nose";

    TextView recibeA1;
    Button vuelta, nuevaA1, reverse, enviar2;
    EditText escribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        recibeA1=findViewById(R.id.textoA2);
        vuelta=findViewById(R.id.bvuelta3);
        nuevaA1=findViewById(R.id.bA1Nueva2);
        reverse=findViewById(R.id.breverse);
        enviar2=findViewById(R.id.enviarA3);
        escribir=findViewById(R.id.escribirNombre2);

        Intent intent= getIntent();
        String message = intent.getStringExtra((A1.MESSAGE_NOMBRE));

        recibeA1.setText(message);

        ActivityResultLauncher<Intent> envioA3=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result-> {
                    Log.d(TAG, "Vuelve cancelado");
                    int code = result.getResultCode();
                    switch (code) {
                        case RESULT_CANCELED:
                            break;
                        case A3.CODIGO_VUELTA_A3:
                            Log.d(TAG, "Vuelve con código, buscar intent");
                            Intent intent1 = result.getData();
                            String mensaje = intent1.getStringExtra(A3.MSG2);
                            recibeA1.setText(mensaje);
                            break;
                    }
                }
        );
        vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(MSG,recibeA1.getText().toString());
                setResult(CODIGO_VUELTA_TEXTO, data);
                finish();
            }
        });
        nuevaA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data=new Intent();
                setResult(CODIGO_VUELTA_TEXTO, data);
                finish();
            }
        });
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data=new Intent();
                data.putExtra(MSG,reverseo(recibeA1.getText().toString()));
                setResult(CODIGO_REVERSO,data);
                finish();
            }
        });
        enviar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(A2.this,A3.class);
                String nombre2 = escribir.getText().toString();
                intent.putExtra(ENVIOTEXT0,nombre2);
                envioA3.launch(intent);
            }
        });
    }
    public String reverseo(String str){
        return new StringBuilder(str).reverse().toString();
    }
}