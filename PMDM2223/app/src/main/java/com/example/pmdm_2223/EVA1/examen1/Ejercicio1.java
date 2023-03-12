package com.example.pmdm_2223.EVA1.examen1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.pmdm_2223.R;

public class Ejercicio1 extends AppCompatActivity {

    EditText n1, n2;
    RadioButton suma,resta,div,multi;
    Button calcular;

    private final String NUMERO1="N1";
    private final String NUMERO2="N2";

    public static final String SUMA="suma";
    public static final String RESTA="resta";
    public static final String MULTI="multi";
    public static final String DIV="div";
    public static final String ENVIO="enviando";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ejercicio1_1);

        n1=findViewById(R.id.examenNum1);
        n2=findViewById(R.id.examenNum2);
        suma=findViewById(R.id.examenSuma);
        resta=findViewById(R.id.examenResta);
        div=findViewById(R.id.examenDiv);
        multi=findViewById(R.id.examenMulti);
        calcular=findViewById(R.id.examenCalcular);

        ActivityResultLauncher miResultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    Log.d(ContentValues.TAG,"VUELVE CANCELADO");
                    int code = result.getResultCode();
                    switch (code){
                        case RESULT_CANCELED:
                            break;
                        //case MainActivity2.CODIGO_SUBIRPOKE:
                        case Ejercicio1_part2.CODIGO_RESET:
                            n1.setText(NUMERO1);
                            n2.setText(NUMERO2);
                            uncheckear();
                            break;
                    }
                });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int num1=Integer.parseInt(n1.getText().toString());
                    int num2=Integer.parseInt(n2.getText().toString());
                    String operacion = obtenerOperacion();
                    if (divCero(num2,operacion)){
                        //inicia actividad
                        Operacion ope=new Operacion(num1,num2,operacion);
                        Intent intent = new Intent(Ejercicio1.this, Ejercicio1_part2.class);
                        intent.putExtra(ENVIO,ope);
                        miResultadoLauncher.launch(intent);
                    }
                }catch (NumberFormatException e){
                    Log.d(TAG,"No se ha podido crear el objeto porque se ha introducido un String");
                }catch (NullPointerException e){
                    Log.d(TAG,"No se ha podido crear el objeto porque el objeto es nulo");
                }
            }
        });
    }

    private boolean divCero(int num2, String operacion) {
        if (num2==0&&operacion.equals(DIV)){
            return false;
        }else return true;
    }

    private String obtenerOperacion() {
        if (suma.isChecked()){
            return SUMA;
        }
        if (resta.isChecked()){
            return RESTA;
        }
        if (div.isChecked()){
            return DIV;
        }
        if (multi.isChecked()){
            return MULTI;
        }
        return null;
    }

    private void uncheckear() {
        if (suma.isChecked()){
            suma.toggle();
        }
        if (resta.isChecked()){
            resta.toggle();
        }
        if (div.isChecked()){
            resta.toggle();
        }
        if (multi.isChecked()){
            resta.toggle();
        }
    }
}