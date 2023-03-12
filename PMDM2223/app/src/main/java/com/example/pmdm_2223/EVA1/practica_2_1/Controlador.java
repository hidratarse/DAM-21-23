package com.example.pmdm_2223.EVA1.practica_2_1;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Controlador implements View.OnClickListener{

    Button b;
    TextView c;
    String id;
    Contador i;

    public Controlador(Contador i, TextView c, Button b){
        this.id=b.getResources().getResourceEntryName(b.getId());
        this.i=i;
        this.b=b;
        this.c=c;
    }

    @Override
    public void onClick(View view) {
        switch (id){
            case "botonreset":
                i.reset();
                break;
            case "botonsuma":
                i.suma();
                break;
            case "botonresta":
                i.resta();
                break;
        }
        c.setText(String.valueOf(i.getCont()));
    }
}
