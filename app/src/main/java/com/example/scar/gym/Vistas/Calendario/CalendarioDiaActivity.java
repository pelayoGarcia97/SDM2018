package com.example.scar.gym.Vistas.Calendario;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaDieta;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Dietas.DietasActivity;

public class CalendarioDiaActivity extends AppCompatActivity {


    private Datos datos;
    private UsuarioRutinaDieta uRD;
    private String dia;
    private int idRutina, idDieta;
    private Button btnEj, btnDieta;
    private String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_dia);

        datos = getIntent().getExtras().getParcelable("datos");
        uRD = getIntent().getExtras().getParcelable("user");
        dia = getIntent().getExtras().getString("dia");
        boolean hayEjercicios = getIntent().getExtras().getBoolean("hayEjercicios");
        idRutina = uRD.getId_rutina();
        idDieta = uRD.getId_dieta();
        dificultad = getIntent().getExtras().getString("dificultad");

        btnEj = findViewById(R.id.btnEj);
        btnDieta = findViewById(R.id.btnDieta);

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnEj.setBackground(draw);
        btnEj.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnDieta.setBackground(draw);
        btnDieta.setTextColor(Color.WHITE);

        if(hayEjercicios){
            btnEj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarMisEjercicios();
                }
            });
        }
        else
            btnEj.setEnabled(false);

        if(idDieta == -1){
            btnDieta.setEnabled(false);
        }
        else
            btnDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarMiDieta();
            }
        });

        this.setTitle("Mi " + dia);
    }

    private void cargarMisEjercicios() {
        Intent intent = new Intent(getApplicationContext(),CalendarioDiaEjercicioActivity.class);
        intent.putExtra("datos", datos);
        intent.putExtra("dia", dia);
        intent.putExtra("idRutina",idRutina);
        intent.putExtra("dificultad",dificultad);
        startActivity(intent);
    }

    private void cargarMiDieta() {
        DiaDieta dd = datos.getDietaByIdAndDay(idDieta, dia);
        Intent intent = new Intent(getApplicationContext(),CalendarioDiaDietaActivity.class);
        intent.putExtra("diaDieta", dd);
        startActivity(intent);
    }
}
