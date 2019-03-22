package com.example.scar.gym.Vistas.Rutinas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.DiaSemana;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.R;

import java.util.List;

public class PreDiasRutinaActivity extends AppCompatActivity {

    private Datos datos;
    private Rutina rutina;
    private String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_dias_rutina);

        datos = getIntent().getExtras().getParcelable("datos");
        dificultad = getIntent().getExtras().getString("dificultad");
        rutina = getIntent().getExtras().getParcelable("rutina");

        this.setTitle(rutina.getNombre() + " (" + dificultad + ")");
        cargarDias();
    }

    private void cargarDias() {
        LinearLayout ll = findViewById(R.id.linear);
        List<DiaSemana> dias = rutina.getEjPorDificultad(dificultad);
        for(final DiaSemana d: dias){
            Button button = new Button(this);
            Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
            button.setBackground(draw);
            button.setText(d.getNombre());
            button.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(950, 150);
            params.topMargin = 15;
            params.bottomMargin = 15;
            ll.addView(button, params);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    abrirEjerciciosDelDia(d);
                }
            });
        }
    }

    private void abrirEjerciciosDelDia(DiaSemana ds) {
        Intent intent = new Intent(getApplicationContext(),DiasRutinaActivity.class);
        intent.putExtra("datos", datos);
        intent.putExtra("rutina", rutina);
        intent.putExtra("dificultad", dificultad);
        intent.putExtra("dia", ds);
        startActivity(intent);
    }


}
