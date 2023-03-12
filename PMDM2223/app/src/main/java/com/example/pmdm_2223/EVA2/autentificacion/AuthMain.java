package com.example.pmdm_2223.EVA2.autentificacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pmdm_2223.EVA2.autentificacion.data.LoginRequest;
import com.example.pmdm_2223.EVA2.autentificacion.data.TokenResponse;
import com.example.pmdm_2223.R;

public class AuthMain extends AppCompatActivity {
    public static final String TOKEN = "";
    EditText user, pass;
    Button logear;
    LoginViewModel vm;
    LiveData<TokenResponse> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_main);

        user = findViewById(R.id.auth_name);
        pass = findViewById(R.id.auth_pass);
        logear = findViewById(R.id.auth_login);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        vm.init();

        data = vm.getTokenLiveData();

        data.observe(this, (dato) ->{
            String token = dato.getToken();
            Intent intent = new Intent(this, AuthLista.class);
            intent.putExtra(TOKEN,token);
            startActivity(intent);
        });

        logear.setOnClickListener(view -> {
            String username = String.valueOf(user.getText());
            String password = String.valueOf(pass.getText());
            vm.getToken(new LoginRequest(username, password));
        });
    }
}