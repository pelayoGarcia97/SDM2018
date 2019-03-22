package com.example.scar.gym.Vistas.Rutinas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Usuario.UsuarioActivity;

public class RutinaDetallesActivity extends AppCompatActivity {

    private Datos datos;
    private Rutina rutina;
    private String dificultad;

    private UsuarioRutinaDieta uRD;
    private Button addRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datos = getIntent().getExtras().getParcelable("datos");
        dificultad = getIntent().getExtras().getString("dificultad");
        rutina = getIntent().getExtras().getParcelable("rutinaConcreta");
        uRD=getIntent().getExtras().getParcelable("user");
        setContentView(R.layout.activity_rutina_detalles);
        cargarInformacion();


    }

    private void addRutina(){
        UsuarioDataSource uDS= new UsuarioDataSource(getApplicationContext());
        uDS.open();
        uDS.addRutinaUser(uRD,rutina.getId(),dificultad);
        uDS.showUserRutinaDieta();
        uDS.close();
        Toast.makeText(getApplicationContext(),"Rutina Añadida Correctamente",Toast.LENGTH_LONG).show();
    }

    private void cargarInformacion() {
        TextView titulo = findViewById(R.id.title);
        TextView txtDificultad = findViewById(R.id.txtDificultad);
        ImageView imagen = findViewById(R.id.imageView);
        TextView descripcion = findViewById(R.id.txtDescripcion);
        Button showDays = findViewById((R.id.btnShowDays));
        addRutina=findViewById(R.id.btnAddRutina);

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        addRutina.setBackground(draw);
        addRutina.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        showDays.setBackground(draw);
        showDays.setTextColor(Color.WHITE);

        showDays.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PreDiasRutinaActivity.class);
                intent.putExtra("datos", datos);
                intent.putExtra("rutina", rutina);
                intent.putExtra("dificultad", dificultad);
                startActivity(intent);
            }
        });

        addRutina.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addRutina();
            }
        });

        titulo.setText(rutina.getNombre());
        txtDificultad.setText(this.dificultad);
        int id = getResources().getIdentifier(rutina.getImagen(), "drawable", getPackageName());
        imagen.setImageResource(id);
        descripcion.setText(rutina.getDescripcion());
    }
}
