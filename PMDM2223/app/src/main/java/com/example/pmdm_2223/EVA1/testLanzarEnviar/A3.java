package com.example.pmdm_2223.EVA1.testLanzarEnviar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class A3 extends AppCompatActivity {

    public static final int CODIGO_VUELTA_A3 = 1;
    public static final String MSG2 = "texto";

    Button volver, nueva1;
    TextView verA2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);

        volver=findViewById(R.id.bvuelta3);
        nueva1=findViewById(R.id.bA1Nueva3);
        verA2=findViewById(R.id.textoA3);

        Intent intent =getIntent();
        String mensahe=intent.getStringExtra((A2.ENVIOTEXT0));

        verA2.setText(mensahe);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(MSG2,verA2.getText().toString());
                setResult(CODIGO_VUELTA_A3,intent);
                finish();
            }
        });

    }
}