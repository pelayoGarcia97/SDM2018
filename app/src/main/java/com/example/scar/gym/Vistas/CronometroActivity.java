package com.example.scar.gym.Vistas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.scar.gym.R;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class CronometroActivity extends AppCompatActivity {

    private Context context;
    GifImageView gifImageView;
    GifDrawable gifDrawable;

    Button btn_start, btn_pause, btn_reset, btn_temporizador;
    Chronometer chronometer;
    Boolean iniciar=false;
    long detenerse;
    private boolean reinicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        btn_start=findViewById(R.id.btn_start);
        btn_pause=findViewById(R.id.btn_pause);
        btn_reset=findViewById(R.id.btn_reset);
        btn_temporizador=findViewById(R.id.btn_temporizador);
        chronometer=findViewById(R.id.chronometero);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometro();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChronometro();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometro();
            }
        });

        btn_temporizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TemporizadorActivity.class);
                startActivity(intent);
            }
        });

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btn_start.setBackground(draw);
        btn_start.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btn_pause.setBackground(draw);
        btn_pause.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btn_reset.setBackground(draw);
        btn_reset.setTextColor(Color.WHITE);

        try {
            gifImageView = findViewById(R.id.splashGif);
            int id = getResources().getIdentifier("crono", "drawable", MainMenuActivity.PACKAGE_NAME);
            gifDrawable = new GifDrawable(getResources(), id);
            gifImageView.setImageDrawable(gifDrawable);
            gifDrawable.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetChronometro() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        detenerse=0;
        gifDrawable.reset();
        if(!iniciar){
            reinicio = true;
            gifImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.crono));// NO FUNCIONA
        }
    }

    private void stopChronometro() {
        if(iniciar) {
            chronometer.stop();
            detenerse = SystemClock.elapsedRealtime() - chronometer.getBase();
            iniciar = false;
            gifDrawable.stop();
        }
    }

    private void startChronometro() {
        if(!iniciar){
            chronometer.setBase(SystemClock.elapsedRealtime() - detenerse);
            chronometer.start();
            iniciar=true;
            final Handler handler = new Handler();
            if(reinicio){
                reinicio = false;
                gifImageView.setImageDrawable(gifDrawable);
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gifDrawable.start();
                }
                }, calcularDelay());
        }
    }

    private long calcularDelay() {
        if(detenerse == 0)
            return 1000;
        String texto = chronometer.getText().toString();
        int min = Integer.parseInt(texto.split(":")[0]);
        int seg = Integer.parseInt(texto.split(":")[1]);
        int numEsperado = ((min * 60 + seg + 1) * 1000);
        Toast.makeText(this, detenerse + "  -  " + numEsperado, Toast.LENGTH_SHORT).show();
        return numEsperado - detenerse + 300;

    }

}
