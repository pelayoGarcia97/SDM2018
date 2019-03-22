package com.example.scar.gym.Vistas.Log_Registro;



import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.Usuario;
import com.example.scar.gym.R;

import java.util.List;

public class RegistroActivity extends AppCompatActivity {

    private TextView txEmail, txPass, txRepass;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txEmail = findViewById(R.id.input_email);
        txPass = findViewById(R.id.input_password);
        txRepass = findViewById(R.id.input_rePassword);

        siguiente = findViewById(R.id.btnSiguiente);

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        siguiente.setBackground(draw);
        siguiente.setTextColor(Color.WHITE);

    }

    public void nextActivity(View view) {
        if (checkPass() && checkNotEmpty() == true && checkEmailLength() == true && isEmailRepeated() == false) {
            Usuario newUser = new Usuario();
            newUser.setEmail(txEmail.getText().toString());
            newUser.setContrasenia(txPass.getText().toString());
            Intent intent = new Intent(getApplicationContext(), RegistroPersonalActivity.class);
            intent.putExtra("user", newUser);

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }


    private boolean checkNotEmpty() {
        String email = txEmail.getText().toString();
        String pass = txPass.getText().toString();
        String repass = txRepass.getText().toString();
        if (email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;

    }

    private boolean checkEmailLength() {
        String email = txEmail.getText().toString();
        String[] emailSplit = email.split("@");
        if (emailSplit.length != 2) {
            Toast.makeText(getApplicationContext(), "Email incorrecto", Toast.LENGTH_LONG).show();
            return false;
        } else {
            String aux = emailSplit[1];
            String[] aux2 = aux.split("\\.");

            if (aux2.length != 2) {
                Toast.makeText(getApplicationContext(), "Email incorrecto", Toast.LENGTH_LONG).show();
                return false;
            } else
                return true;
        }
    }

    private boolean isEmailRepeated() {
        UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
        uDS.open();
        List<Usuario> usersDB = uDS.getUsers();
        String email = txEmail.getText().toString();
        for (Usuario user : usersDB
                ) {
            if (user.getEmail().equals(email)) {
                Toast.makeText(getApplicationContext(), "Email ya usado para otra cuenta", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private boolean checkPass() {
        if (txPass.getText().toString().equals(txRepass.getText().toString()))
            return true;
        else {
            Toast.makeText(getApplicationContext(), "Contraseñas Distintas", Toast.LENGTH_LONG).show();
            return false;
        }

    }
}