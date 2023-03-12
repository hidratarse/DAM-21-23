package com.example.pmdm_2223.EVA1.txi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class ReciboViaje extends AppCompatActivity {

    TextView rnombre, rdni, rdir, rciuo, rfei, rhoid, rciud, rfev,rhvu,txtf,txth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_viaje);

        rnombre=findViewById(R.id.recibeNombre);
        rdni=findViewById(R.id.recibeDNI);
        rdir=findViewById(R.id.reciberDir);
        rciuo=findViewById(R.id.recibeCiuO);
        rfei=findViewById(R.id.recibeFida);
        rhoid=findViewById(R.id.recibeHida);
        rciud=findViewById(R.id.recibeCiuD);
        rfev=findViewById(R.id.recibeFvuelta);
        rhvu=findViewById(R.id.recibeHvuelta);
        txtf=findViewById(R.id.textoFecha);
        txth=findViewById(R.id.textoHora);

        Viajero recibeViajero = (Viajero)getIntent().getSerializableExtra("ENVIO");

        rnombre.setText(recibeViajero.getNombre());
        rdni.setText(recibeViajero.getDNI());
        rdir.setText(recibeViajero.getDireccion());
        rciuo.setText(recibeViajero.getCiudadOrigen());
        rfei.setText(recibeViajero.getFechaIda().toString());
        rhoid.setText(recibeViajero.getHoraIda());
        rciud.setText(recibeViajero.getCiudadDestino());
        if (recibeViajero.getIdavuelta()) {
            txtf.setVisibility(View.VISIBLE);
            txth.setVisibility(View.VISIBLE);
            rfev.setText(recibeViajero.getFechaVuelta().toString());
            rhvu.setText(recibeViajero.getHoraVuelta());
        }

    }
}