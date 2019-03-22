package com.example.scar.gym.Vistas.Calendario;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaSemana;
import com.example.scar.gym.Datos.Ejercicio;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.CronometroActivity;
import com.example.scar.gym.Vistas.Ejercicios.EjerciciosViewRecyclerView;
import com.example.scar.gym.Vistas.TemporizadorActivity;

import java.util.ArrayList;
import java.util.List;

public class CalendarioDiaEjercicioActivity  extends AppCompatActivity implements EjerciciosViewRecyclerView.ItemClickListener{

    private Datos datos;
    private int idRutina;
    private String dia;
    private String dificultad;

    private EjerciciosViewRecyclerView adapter;
    private Button btnCrono, btnTempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_dia_ejercicio);

        datos = getIntent().getExtras().getParcelable("datos");
        idRutina = getIntent().getExtras().getInt("idRutina");
        dia = getIntent().getExtras().getString("dia");
        dificultad = getIntent().getExtras().getString("dificultad");

        btnCrono = findViewById(R.id.btnAddDieta);
        btnTempo = findViewById(R.id.btnTempo);

        btnCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),CronometroActivity.class);
               startActivity(intent);
            }
        });
        btnTempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TemporizadorActivity.class);
               startActivity(intent);
            }
        });

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnCrono.setBackground(draw);
        btnCrono.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnTempo.setBackground(draw);
        btnTempo.setTextColor(Color.WHITE);

        cargarEjerciciosVista();

        this.setTitle("Mi " + dia + " (Ejercicios)");
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
        if(idRutina == -1)
            return new ArrayList<Ejercicio>();
        Rutina rutina = datos.getRutinaById(idRutina);
        List<DiaSemana> dif = rutina.getEjPorDificultad(dificultad);
        List<String> ejercicios = null;
        for(DiaSemana ds: dif){
            if(dia.equals(ds.getNombre()))
                ejercicios = ds.getEjercicios();
        }
        List<Ejercicio> lista = new ArrayList<>();
        for(String ejName: ejercicios){
            lista.add(conseguirEjercicios(ejName));
        }
        return lista;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private Ejercicio conseguirEjercicios(String ejName){
        return datos.buscarEjercicio(ejName);
    }
}
