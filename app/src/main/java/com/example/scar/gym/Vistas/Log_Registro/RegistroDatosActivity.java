package com.example.scar.gym.Vistas.Log_Registro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Usuario;
import com.example.scar.gym.R;

import java.util.Date;
import java.util.List;

public class RegistroDatosActivity extends AppCompatActivity {


    private TextView txEdad,txPeso,txAltura;
    private Button siguiente;
    private Usuario newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        newUser = getIntent().getExtras().getParcelable("user");

        txEdad=findViewById(R.id.input_edad);
        txPeso = findViewById(R.id.input_peso);
        txAltura=findViewById(R.id.input_altura);
        siguiente= findViewById(R.id.btnSiguiente);

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        siguiente.setBackground(draw);
        siguiente.setTextColor(Color.WHITE);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNotEmpty() && checkCorrect()){
                    int edad= Integer.parseInt(txEdad.getText().toString());
                    double peso=Double.parseDouble(txPeso.getText().toString());
                    int altura = Integer.parseInt(txAltura.getText().toString());

                    UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
                    uDS.open();
                    uDS.createUser(newUser);
                    List<Usuario> users=uDS.getUsers();
                    for (Usuario u:users) {
                        Log.d("Users",u.toString());
                    }
                    Usuario user=uDS.getUser(newUser.getEmail());
                    int id = user.getId();
                    uDS.createEdadAlturaPeso(id,edad,peso,altura,new Date());
                    uDS.close();
                    Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkNotEmpty(){
        if(txEdad.getText().toString().isEmpty() || txAltura.getText().toString().isEmpty() || txPeso.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show();
            return  false;
        }
        else
            return true;
    }

    private boolean checkCorrect(){
        if(Integer.parseInt(txEdad.getText().toString()) < 0 || Integer.parseInt(txAltura.getText().toString()) < 100 ||
                Integer.parseInt(txAltura.getText().toString()) > 220 || Double.parseDouble(txPeso.getText().toString()) < 40){
            Toast.makeText(getApplicationContext(), "Algun valor es incorrecto", Toast.LENGTH_LONG).show();
            return  false;
        }
        else
            return true;
    }
}
