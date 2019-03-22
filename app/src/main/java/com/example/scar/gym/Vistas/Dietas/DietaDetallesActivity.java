package com.example.scar.gym.Vistas.Dietas;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaDieta;
import com.example.scar.gym.Datos.Dieta;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Rutinas.PreDiasRutinaActivity;

import java.util.List;

public class DietaDetallesActivity extends AppCompatActivity implements DietaDetalleViewRecyclerView.ItemClickListener {

    private Datos datos;
    private Dieta dieta;

    private UsuarioRutinaDieta uRD;
    private Button addDieta;

    DietaDetalleViewRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datos = getIntent().getExtras().getParcelable("datos");
        dieta = getIntent().getExtras().getParcelable("dietaConcreta");
        uRD=getIntent().getExtras().getParcelable("user");
        setContentView(R.layout.activity_dieta_detalle);
        cargarInformacion();

        cargarEjerciciosVista();
    }

    private void addDieta(){
        UsuarioDataSource uDS= new UsuarioDataSource(getApplicationContext());
        uDS.open();
        uDS.addDietaUser(uRD,dieta.getId());
        uDS.showUserRutinaDieta();
        uDS.close();
        Toast.makeText(getApplicationContext(),"Dieta Añadida Correctamente",Toast.LENGTH_LONG).show();
    }

    private void cargarInformacion() {
        addDieta=findViewById(R.id.btnAddDieta);
        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        addDieta.setBackground(draw);
        addDieta.setTextColor(Color.WHITE);

        addDieta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addDieta();
            }
        });
    }

    private void cargarEjerciciosVista() {
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvDieta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DietaDetalleViewRecyclerView(this, dieta.getDiasSemana(), false);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
