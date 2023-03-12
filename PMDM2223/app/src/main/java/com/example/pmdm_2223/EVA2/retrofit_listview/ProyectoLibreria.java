package com.example.pmdm_2223.EVA2.retrofit_listview;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmdm_2223.EVA2.ejemplo_observador.EjemploObservador;
import com.example.pmdm_2223.EVA2.retrofit_listview.data.Volume;
import com.example.pmdm_2223.EVA2.retrofit_listview.data.VolumesResponse;
import com.example.pmdm_2223.R;

public class ProyectoLibreria extends AppCompatActivity {

    TextView busqueda, autor;
    Button buscar;
    RecyclerView lista;
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booksearch);

        busqueda = findViewById(R.id.id_busqueda);
        autor = findViewById(R.id.id_autor);
        buscar = findViewById(R.id.id_buscar);
        lista = findViewById(R.id.id_lista_libros);
        pg = findViewById(R.id.id_libros_progressBar);

        pg.setVisibility(View.INVISIBLE);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, (dato)->{
            adapter.setResults(dato.getItems());
        });

        adapter.setClickListener((view, v) ->{
            Toast.makeText(ProyectoLibreria.this,"Pulsado "+ v, Toast.LENGTH_SHORT).show();
        });

        lista.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = layoutManager.getItemCount();
                int ultimoVisible = layoutManager.findLastVisibleItemPosition();
                Log.d(TAG,String.valueOf(ultimoVisible));
                if (total == (ultimoVisible + 1)) {
                    vm.extendVolumes(busqueda.getText().toString(),autor.getText().toString(),total+10);
                }
            }
        });

        buscar.setOnClickListener(view -> {
            pg.setVisibility(View.VISIBLE);
            vm.searchVolumes(busqueda.getText().toString(),autor.getText().toString());
            pg.setVisibility(View.INVISIBLE);
        });
    }
}