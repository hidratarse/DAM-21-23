package com.example.pmdm_2223.EVA1.txi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pmdm_2223.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvioViaje extends AppCompatActivity {
    TextView switchI, switchV;
    EditText horaIda, horaVuelta, fechaIda,fechaVuelta, DNI, direccionRecogida, nombre;
    Spinner ciudadesO, ciudadesD;
    Switch switchIdaVuelta;
    Button enviar;
    Viajero viajero=new Viajero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envioviaje);

        horaIda=findViewById(R.id.horaIda);
        horaVuelta=findViewById(R.id.horaVuelta);
        fechaIda=findViewById(R.id.fechaIda);
        fechaVuelta=findViewById(R.id.fechaVuelta);
        DNI=findViewById(R.id.vDNI);
        direccionRecogida=findViewById(R.id.vDireccion);
        nombre=findViewById(R.id.vNombre);
        ciudadesO=findViewById(R.id.ciudadesOrigen);
        ciudadesD=findViewById(R.id.ciudadesDestino);
        switchIdaVuelta=findViewById(R.id.switchIdaVuelta);
        switchI=findViewById(R.id.switchIda);
        switchV=findViewById(R.id.switchVuelta);
        enviar=findViewById(R.id.envioViaje);

        switchIdaVuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchIdaVuelta.isChecked()){
                    switchV.setVisibility(View.VISIBLE);
                    switchI.setVisibility(View.INVISIBLE);
                    fechaVuelta.setVisibility(View.VISIBLE);
                    horaVuelta.setVisibility(View.VISIBLE);
                } else {
                    switchV.setVisibility(View.INVISIBLE);
                    switchI.setVisibility(View.VISIBLE);
                    fechaVuelta.setVisibility(View.INVISIBLE);
                    horaVuelta.setVisibility(View.INVISIBLE);
                }
            }
        });
        fechaIda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.fechaIda:
                        showDatePickerDialog(fechaIda);
                }
            }
        });

        fechaVuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fechaVuelta);
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
                    String nombreV= String.valueOf(nombre.getText());
                    String direccion= String.valueOf(direccionRecogida.getText());
                    String DNIv= String.valueOf(DNI.getText());
                    String ciudadOrigenv=ciudadesO.getSelectedItem().toString();
                    String ciudadDestinov=ciudadesD.getSelectedItem().toString();
                    String horaIdav= String.valueOf(horaIda.getText());
                    Date fechaIdav=sdformat.parse(String.valueOf(fechaIda.getText()));
                    Date fechaVueltav;
                    String horaVueltav;

                    Boolean fechasValidadas;
                    Boolean DNIValidado=validadoDNI(DNIv);
                    Boolean ciudadesValidadas=validadoCiudades(ciudadOrigenv,ciudadDestinov);

                    if (!DNIValidado) {
                        DNI.setText("DNI NO VALIDO");
                    }

                    if (switchIdaVuelta.isChecked()) {
                        horaVueltav= String.valueOf(horaVuelta.getText());
                        fechaVueltav=sdformat.parse(String.valueOf(fechaVuelta.getText()));
                        fechasValidadas=validadoFechas(fechaIdav,fechaVueltav );
                        if (!fechasValidadas) {
                            fechaIda.setText("ERROR DE FECHAS");
                            fechaVuelta.setText("ERROR DE FECHAS");
                        }
                        if ( DNIValidado && fechasValidadas && ciudadesValidadas) {
                            viajero=new Viajero(nombreV, direccion, DNIv,ciudadOrigenv,ciudadDestinov,horaIdav,horaVueltav,fechaIdav,fechaVueltav);
                            viajero.setIdavuelta(true);
                            lanzar();
                        }
                    }else{
                        if ( DNIValidado && ciudadesValidadas) {
                            viajero=new Viajero(nombreV, direccion, DNIv,ciudadOrigenv,ciudadDestinov,horaIdav,fechaIdav);
                            lanzar();
                        }
                    }
                } catch (ParseException e) {}
            }
        });
    }

    private void showDatePickerDialog(EditText fecha){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                fecha.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    void lanzar(){
        Intent intento = new Intent(this, ReciboViaje.class);
        intento.putExtra("ENVIO",viajero);
        startActivity(intento);
    }

    public boolean validadoCiudades(String ciudadOrigenv, String ciudadDestinov){
        if (ciudadOrigenv.equals(ciudadDestinov)) {
            return false;
        }else return true;
    }

    public boolean validadoFechas(Date d1, Date d2){
        if(d1.compareTo(d2) < 0) {
            return true;
        }else
            return false;
    }

    public boolean validadoDNI(String DNIv){
        Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        Matcher mat = pat.matcher(DNIv);
        //email ^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$
        if (mat.matches()) {
            return true;
        }else return false;
    }

    /*
    * eHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        eHora.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    *
    *   Devuelve un string con la hora.
    * */
}