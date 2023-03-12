package com.example.pmdm_2223.EVA1.prueba_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pmdm_2223.R;

public class pruebaEditText extends AppCompatActivity {

    TextView nombre, mail,usuario,todo;
    EditText editNombre, editMail, editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebaeditext);

        todo=findViewById(R.id.verTodo);
        editNombre=findViewById(R.id.editNombre);
        editMail=findViewById(R.id.editMail);
        editUser=findViewById(R.id.editUser);

        TextView.OnEditorActionListener manejador = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_GO) {
                    todo.setText(
                            String.format(
                                    "Hola %s\nTus datos:\n%s\n%s",
                                    editNombre.getText(),
                                    editMail.getText(),
                                    editUser.getText()
                            )
                    );
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                }
                return false;
            }
        };
        editNombre.setOnEditorActionListener(manejador);
        editMail.setOnEditorActionListener(manejador);
        editUser.setOnEditorActionListener(manejador);
    }
}