package com.example.scar.gym.Vistas.Dietas;

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
import com.example.scar.gym.Vistas.Rutinas.RutinaDetallesActivity;
import com.example.scar.gym.Vistas.Rutinas.RutinasViewRecyclerView;

public class DietasActivity extends AppCompatActivity implements DietasViewRecyclerView.ItemClickListener {

    DietasViewRecyclerView adapter;

    private Datos datos;

    private UsuarioRutinaDieta uRD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        datos = getIntent().getExtras().getParcelable("datos");
        uRD = getIntent().getExtras().getParcelable("user");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);

        cargarEjerciciosVista();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getApplicationContext(), DietaDetallesActivity.class);
        intent.putExtra("datos", datos);
        intent.putExtra("dietaConcreta", datos.getDietas().get(position));
        intent.putExtra("user", uRD);
        startActivity(intent);
    }


    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvDietas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DietasViewRecyclerView(this, datos.getDietas(), false);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
