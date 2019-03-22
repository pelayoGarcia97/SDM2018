package com.example.scar.gym.Vistas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Log_Registro.LogInActivity;
import com.example.scar.gym.Vistas.Log_Registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = findViewById(R.id.btn_login);
        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnLogin.setBackground(draw);
        btnLogin.setTextColor(Color.WHITE);

        Button signIn = findViewById(R.id.btn_signIn);
        Drawable drawSI = ContextCompat.getDrawable(this, R.drawable.custom_button);
        signIn.setBackground(drawSI);
        signIn.setTextColor(Color.WHITE);
    }

    public void irLoginActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(intent);
    }

    public void irSignInActivity(View view){
        Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(intent);
    }
}
