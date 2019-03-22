package com.example.scar.gym.Vistas.Rutinas;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaSemana;
import com.example.scar.gym.Datos.Ejercicio;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Ejercicios.EjerciciosViewRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiasRutinaActivity extends AppCompatActivity implements EjerciciosViewRecyclerView.ItemClickListener{

    private DiaSemana dia;
    private Datos datos;

    private EjerciciosViewRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_rutina);

        dia = getIntent().getExtras().getParcelable("dia");
        datos = getIntent().getExtras().getParcelable("datos");
        Rutina rutina = getIntent().getExtras().getParcelable("rutina");
        String dificultad = getIntent().getExtras().getString("dificultad");

        this.setTitle(rutina.getNombre() + " (" + dificultad + ") - " + dia.getNombre());
        cargarEjerciciosVista();
    }

    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvEjercicios);

        List<Ejercicio> lista = prepararEjercicios();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EjerciciosViewRecyclerView(this, lista, 0);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private List<Ejercicio> prepararEjercicios() {
        List<Ejercicio> lista = new ArrayList<>();
        for(String ejName: dia.getEjercicios()){
            lista.add(conseguirEjercicios(ejName));
        }
        return lista;
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    private Ejercicio conseguirEjercicios(String ejName){
        return datos.buscarEjercicio(ejName);
    }
}
