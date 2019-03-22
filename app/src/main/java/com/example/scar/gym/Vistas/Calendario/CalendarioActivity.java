package com.example.scar.gym.Vistas.Calendario;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaSemana;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;

import java.util.List;

public class CalendarioActivity extends AppCompatActivity {

    Button btnLunes, btnMartes, btnMiercoles, btnJueves, btnViernes, btnSabado, btnDomingo;
    private Datos datos;
    private UsuarioRutinaDieta uRD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        datos = getIntent().getExtras().getParcelable("datos");
        uRD = getIntent().getExtras().getParcelable("user");

        btnLunes = findViewById(R.id.btnLunes);
        btnMartes = findViewById(R.id.btnMartes);
        btnMiercoles = findViewById(R.id.btnMiercoles);
        btnJueves = findViewById(R.id.btnJueves);
        btnViernes = findViewById(R.id.btnViernes);
        btnSabado = findViewById(R.id.btnSabado);
        btnDomingo = findViewById(R.id.btnDomingo);

        cargarBotones();
    }

    private void cargarBotones() {
        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnLunes.setBackground(draw);
        btnLunes.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnMartes.setBackground(draw);
        btnMartes.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnMiercoles.setBackground(draw);
        btnMiercoles.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnJueves.setBackground(draw);
        btnJueves.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnViernes.setBackground(draw);
        btnViernes.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnSabado.setBackground(draw);
        btnSabado.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnDomingo.setBackground(draw);
        btnDomingo.setTextColor(Color.WHITE);

        btnLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Lunes");
            }
        });
        btnMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Martes");
            }
        });
        btnMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Miercoles");
            }
        });
        btnJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Jueves");
            }
        });
        btnViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Viernes");
            }
        });
        btnSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Sabado");
            }
        });
        btnDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDiaSemana("Domingo");
            }
        });

        this.setTitle("Mi Semana");
    }

    private void cargarDiaSemana(String dia) {
        boolean hayEjercicios = comprobarEjercicios(dia);
        UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
        uDS.open();
        int idRutina = uDS.getRutinaUser(uRD);
        int idDieta = uDS.getDietaUser(uRD);
        String dificultad = uDS.getDificultadRutina(uRD);

        uDS.close();

        uRD.setId_dieta(idDieta);
        uRD.setId_rutina(idRutina);

        Intent intent = new Intent(getApplicationContext(),CalendarioDiaActivity.class);
        intent.putExtra("datos", datos);
        intent.putExtra("user",uRD);
        intent.putExtra("dia",dia);
        intent.putExtra("hayEjercicios",hayEjercicios);
        intent.putExtra("dificultad",dificultad);
        startActivity(intent);
    }

    private boolean comprobarEjercicios(String dia) {
        UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
        uDS.open();
        int idRutina = uDS.getRutinaUser(uRD);
        String dif = uDS.getDificultadRutina(uRD);
        uDS.close();
        if(idRutina == -1)
            return false;
        List<DiaSemana> dias = datos.getRutinaById(idRutina).getEjPorDificultad(dif);
        for(DiaSemana ds: dias){
            if(ds.getNombre().equals(dia))
                return true;
        }
        return false;
    }

}
