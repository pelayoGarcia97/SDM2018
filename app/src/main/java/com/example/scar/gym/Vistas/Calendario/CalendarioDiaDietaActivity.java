package com.example.scar.gym.Vistas.Calendario;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Dietas.DietaDetalleViewRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CalendarioDiaDietaActivity extends AppCompatActivity implements DietaDetalleViewRecyclerView.ItemClickListener {

    private DiaDieta dd;
    private ArrayList<DiaDieta> dias;
    private DietaDetalleViewRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_dia_dieta);

        dd = getIntent().getExtras().getParcelable("diaDieta");
        dias = new ArrayList<DiaDieta>();
        dias.add(dd);
        cargarEjerciciosVista();

        this.setTitle("Mi " + dd.getNombre() + " (Dieta)");
    }

    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvDieta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DietaDetalleViewRecyclerView(this, dias, false);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
