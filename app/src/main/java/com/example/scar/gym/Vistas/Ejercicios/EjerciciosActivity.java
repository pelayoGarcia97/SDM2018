package com.example.scar.gym.Vistas.Ejercicios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.R;

public class EjerciciosActivity extends AppCompatActivity implements EjerciciosViewRecyclerView.ItemClickListener {

    EjerciciosViewRecyclerView adapter;

    private Datos datos;
    private String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        datos = getIntent().getExtras().getParcelable("datos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        cargarEjerciciosVista();
    }

    @Override
    public void onItemClick(View view, int position) {
    }


    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvEjercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EjerciciosViewRecyclerView(this, datos.getEjercicios(), 1);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
