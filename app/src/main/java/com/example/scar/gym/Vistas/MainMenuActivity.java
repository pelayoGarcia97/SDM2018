package com.example.scar.gym.Vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Datos;
import com.example.scar.gym.Datos.Usuario;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Calendario.CalendarioActivity;
import com.example.scar.gym.Vistas.Dietas.DietasActivity;
import com.example.scar.gym.Vistas.Ejercicios.EjerciciosActivity;
import com.example.scar.gym.Vistas.Log_Registro.LogInActivity;
import com.example.scar.gym.Vistas.Rutinas.RutinasActivity;
import com.example.scar.gym.Vistas.Usuario.UsuarioActivity;

public class MainMenuActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    private Datos datos;
    private Context context;

    private Button rutina, dieta, ejercicio, contarPasos, usuario, calendario;

    private UsuarioDataSource uDS;
    private UsuarioRutinaDieta uRD;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        settings=getSharedPreferences(LogInActivity.nombrePreferencias,MODE_PRIVATE);
        checkPreferences();

        PACKAGE_NAME = getApplicationContext().getPackageName();
        context = this;

        rutina = findViewById(R.id.btnRutina);
        dieta = findViewById(R.id.btnDietas);
        ejercicio = findViewById(R.id.btnEjercicios);
        contarPasos = findViewById(R.id.btnCronometro);
        usuario = findViewById(R.id.btnUser);
        calendario = findViewById(R.id.btnCalendario);

        datos = new Datos(this);

        uRD = getIdUser();
        uDS= new UsuarioDataSource(getApplicationContext());
        uDS.open();
        if(uDS.checkUserRutinaDietaExist(uRD)==false){
            uDS.createRutinaDieta(uRD);
        }
        uDS.close();
        rutina.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RutinasActivity.class);
                pasarDatos(intent);
            }

        });

        dieta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DietasActivity.class);
                pasarDatos(intent);
            }

        });
        ejercicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EjerciciosActivity.class);
                pasarDatos(intent);
            }

        });
        contarPasos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(),CalendarioActivity.class);     //PARA PROBAR
                Intent intent = new Intent(getApplicationContext(),CronometroActivity.class);
                pasarDatos(intent);
            }

        });
        usuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UsuarioActivity.class);
                pasarDatos(intent);
            }

        });
        calendario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CalendarioActivity.class);
                pasarDatos(intent);
            }

        });


    }

    private void pasarDatos(Intent intent) {
        intent.putExtra("datos", datos);
        intent.putExtra("user",uRD);
        startActivity(intent);
    }
    private void checkPreferences() {
        String user = settings.getString("usuario", "null");
        String pass = settings.getString("pass", "null");
        if (user.equals("null") || pass == "null") {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
    }

    private UsuarioRutinaDieta getIdUser(){
        UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());

        uDS.open();
        String email = settings.getString("usuario",null);
        Usuario user=uDS.getUser(email);
        UsuarioRutinaDieta uRD = new UsuarioRutinaDieta(user.getId());
        uDS.close();
        return uRD;
    }

}
