package com.example.pmdm_2223.EVA1.prueba_listado;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmdm_2223.R;


public class MainActivity2 extends AppCompatActivity {

    public static final int CODIGO_SUBIRPOKE=2;

    private Uri uriCapturada;

    EditText nombre, numero;
    Button vuelta, añadir, eliminar;
    PokemonDAO pokeDAO;
    ImageView imgSprite;
    PokemonDAO pokemonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre=findViewById(R.id.nuevoNombrePkmn);
        numero=findViewById(R.id.nuevoNumPkmn);
        vuelta=findViewById(R.id.pokeAtras);
        añadir=findViewById(R.id.subirPoke);
        imgSprite=findViewById(R.id.pokeImg);
        eliminar=findViewById(R.id.eliminarPoke);

        Pokemon pokemon = (Pokemon)getIntent().getSerializableExtra("ENVIO");

        if (pokemon!=null){
            Log.d(TAG,"El pokemon es nulo");
            eliminar.setVisibility(View.VISIBLE);
            añadir.setVisibility(View.INVISIBLE);

            nombre.setText(pokemon.getNombre());
            numero.setText(String.valueOf(pokemon.getNumero()));
            imgSprite.setImageURI(Uri.parse(pokemon.getSprite()));
        }

        TextView.OnEditorActionListener manejador = (textView, i, keyEvent) -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            return false;
        };
        nombre.setOnEditorActionListener(manejador);
        numero.setOnEditorActionListener(manejador);

        ActivityResultLauncher<Intent>imgResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();

                        uriCapturada = data.getData();
                        getContentResolver().takePersistableUriPermission(uriCapturada, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        imgSprite.setImageURI(uriCapturada);

                    }
                }
        );

        imgSprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uriCapturada);
                imgResult.launch(i);
            }
        });

        vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uriCapturada != null) {
                    String pokeNom=nombre.getText().toString();
                    int pokeNum=Integer.parseInt(numero.getText().toString());

                    String pokeSprite=uriCapturada.toString();
                    Log.d(TAG,"La ruta es"+pokeSprite);
                    Pokemon nuevoPoke = new Pokemon(pokeNom,pokeSprite,pokeNum);
                    Intent intent = new Intent(MainActivity2.this,Listado.class);
                    intent.putExtra("ENVIO",nuevoPoke);
                    setResult(CODIGO_SUBIRPOKE,intent);
                    finish();
                    /*if (pokeValidado(pokeNum, pokeNom)){

                    }*/
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numero1=Integer.parseInt(String.valueOf(numero.getText()));
                Pokemon poke = pokeDAO.findByNumber(numero1);
                pokeDAO.delete(poke);
                finish();
            }
        });
    }
    public boolean pokeValidado(int pokeNum, String pokeNom){
        Pokemon pokeTemp= pokeDAO.findByNumber(pokeNum);
        return false;
    }
}