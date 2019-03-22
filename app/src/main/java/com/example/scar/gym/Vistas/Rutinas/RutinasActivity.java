package com.example.scar.gym.Vistas.Rutinas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;

public class RutinasActivity extends AppCompatActivity implements RutinasViewRecyclerView.ItemClickListener {

    RutinasViewRecyclerView adapter;

    private Datos datos;
    private String dificultad;

    private UsuarioRutinaDieta uRD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        datos = getIntent().getExtras().getParcelable("datos");
        uRD=getIntent().getExtras().getParcelable("user");
        // Para evitar el no cargar con una dificultad
        ponerDificultad("principiante");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        elegirDificultad();
        cargarEjerciciosVista();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getApplicationContext(), RutinaDetallesActivity.class);
        intent.putExtra("datos", datos);
        intent.putExtra("rutinaConcreta", datos.getRutinas().get(position));
        intent.putExtra("dificultad", this.dificultad);
        intent.putExtra("user",uRD);
        startActivity(intent);
    }


    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvEjercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RutinasViewRecyclerView(this, datos.getRutinas(), false);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void elegirDificultad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dificultad");
        // Crear la lista
        String[] opciones = {"Principiante", "Intermedio", "Avanzado"};
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        ponerDificultad("principiante");
                        break;
                    case 1:
                        ponerDificultad("intermedio");
                        break;
                    case 2:
                        ponerDificultad("avanzado");
                        break;
                }
            }
        });
        // Crear y mostrar el Dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void ponerDificultad(String d) {
        this.dificultad = d;
        datos.cambiarDificultad(d);
    }
}