package com.example.scar.gym.Vistas.Log_Registro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.MyDBHelper;
import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Ejercicios.EjerciciosActivity;
import com.example.scar.gym.Vistas.MainMenuActivity;
import com.example.scar.gym.Vistas.Rutinas.RutinasActivity;

import static android.content.Context.MODE_PRIVATE;

public class LogInActivity extends AppCompatActivity {
    private TextView singIn;
    private Button login;

    private TextView inEmail,inPass;

    SharedPreferences settings;
    public static final String nombrePreferencias = "PreferenciasUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        settings=getSharedPreferences(nombrePreferencias,MODE_PRIVATE);

        singIn= findViewById(R.id.link_signup);
        login=findViewById(R.id.btn_login);
        inEmail=findViewById(R.id.input_email);
        inPass = findViewById(R.id.input_password);


        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
            }

        });

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        login.setBackground(draw);
        login.setTextColor(Color.WHITE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
                uDS.open();
                if(uDS.checkUserExist(inEmail.getText().toString(),inPass.getText().toString())){
                    guardarPreferences();
                    Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                    //Cargar la fila del usuario con su rutina y dieta en la BBDD
                    //intent.putExtra("datosCargados", );+
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"No existe",Toast.LENGTH_LONG).show();
                uDS.close();

            }

        });
    }
    private void guardarPreferences(){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario",inEmail.getText().toString());
        editor.putString("pass",inPass.getText().toString());
        editor.commit();
    }
}