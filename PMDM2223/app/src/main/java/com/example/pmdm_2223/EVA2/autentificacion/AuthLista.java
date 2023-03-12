
package com.example.pmdm_2223.EVA2.autentificacion;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.pmdm_2223.EVA2.autentificacion.data.QuestionResponse;
import com.example.pmdm_2223.R;

import java.util.List;

public class AuthLista extends AppCompatActivity {
    LoginViewModel vm;
    LiveData <List<QuestionResponse>> listLiveData;
    RecyclerView recyclerView;
    QuestionsAdapter adapter;
    Button add, refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_lista);

        recyclerView = findViewById(R.id.auth_rv);
        add = findViewById(R.id.auth_add);
        refresh = findViewById(R.id.auth_refresh);

        adapter = new QuestionsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();

        String token = intent.getStringExtra(AuthMain.TOKEN);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        vm.init();

        listLiveData = vm.getQuestionListLiveData();

        listLiveData.observe(this, (dato) ->{
            adapter.setResults(dato);
        });

        add.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, AuthDetalle.class);
            intent1.putExtra("TOKEN", token);
            startActivity(intent1);
        });

        refresh.setOnClickListener(view -> {
            vm.getQuestions(token);
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                vm.getQuestions(token);
            }
        });
        vm.getQuestions(token);
    }
}