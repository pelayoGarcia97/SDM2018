package com.example.scar.gym.Vistas.Ejercicios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scar.gym.Datos.Ejercicio;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.R;

public class EjercicioDetallesActivity extends AppCompatActivity {

    private Ejercicio ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ejercicio = getIntent().getExtras().getParcelable("ejercicioConcreto");
        setContentView(R.layout.activity_ejercicio_detalles);
        cargarInformacion();
    }

    private void cargarInformacion() {
        TextView titulo = findViewById(R.id.title);
        TextView txtDificultad = findViewById(R.id.txtDificultad);
        ImageView imagen = findViewById(R.id.imageView);
        TextView descripcion = findViewById(R.id.txtDescripcion);

        titulo.setText(ejercicio.getNombre());
        txtDificultad.setText(this.ejercicio.getDificultad());
        int id = getResources().getIdentifier("perderpeso", "drawable", getPackageName());
        imagen.setImageResource(id);
        descripcion.setText(ejercicio.getDescripcion());
    }
}
