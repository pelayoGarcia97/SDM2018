package com.example.scar.gym.Vistas;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.R;

import java.util.Locale;

public class TemporizadorActivity extends AppCompatActivity {
    private EditText etTextInput;
    private TextView textTemporizador;
    private Button btnSet;
    private Button btnTemporizador;
    private Button btnReset;

    private CountDownTimer temporizador;
    private long tiempoInicio;
    private long timeLeftInMilliseconds = tiempoInicio;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        etTextInput= findViewById(R.id.et_text_input);
        textTemporizador= findViewById(R.id.temporizador_text);
        btnSet=findViewById(R.id.btn_set);
        btnTemporizador= findViewById(R.id.btn_temporizador);
        btnReset= findViewById(R.id.btn_reset);

        Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnSet.setBackground(draw);
        btnSet.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnTemporizador.setBackground(draw);
        btnTemporizador.setTextColor(Color.WHITE);
        draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
        btnReset.setBackground(draw);
        btnReset.setTextColor(Color.WHITE);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etTextInput.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(TemporizadorActivity.this, "El campo no puede estar vacio", Toast.LENGTH_SHORT);
                    return;
                }

                long millisInput= Long.parseLong(input) * 60000;
                if(millisInput==0){
                    Toast.makeText(TemporizadorActivity.this, "Por favor, meta un numero positivo", Toast.LENGTH_SHORT);
                    return;
                }
                setTime(millisInput);
                etTextInput.setText("");
                updateButtons();
            }
        });

        btnTemporizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
                updateButtons();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                updateButtons();
            }
        });

        updateTimer();
    }

    private void startStop() {
        if(timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }

    }

    private void setTime(long milliseconds){
        tiempoInicio = milliseconds;
        resetTimer();
    }


    private void startTimer() {
        temporizador = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds=l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                btnTemporizador.setText("Inicio");
                btnTemporizador.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }
        }.start();

        timerRunning=true;
        btnTemporizador.setText("Pausa");
        btnReset.setVisibility(View.INVISIBLE);
    }

    private void stopTimer() {
        temporizador.cancel();
        btnTemporizador.setText("Inicio");
        timerRunning=false;
        btnReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        timeLeftInMilliseconds = tiempoInicio;
        updateTimer();
        btnReset.setVisibility(View.INVISIBLE);
        btnTemporizador.setVisibility(View.VISIBLE);
    }

    private void updateTimer() {
        int hours = (int) (timeLeftInMilliseconds/1000) / 3600;
        int minutes= (int) ((timeLeftInMilliseconds/1000) %3600) /60;
        int seconds = (int) timeLeftInMilliseconds%60000/1000;

        String timeLeftFormated;
        if (hours>0){
            timeLeftFormated = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, seconds);
        }
        else{
            timeLeftFormated = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        }

        textTemporizador.setText(timeLeftFormated);
    }

    private void updateButtons() {
        if(timerRunning){
            etTextInput.setVisibility(View.INVISIBLE);
            btnSet.setVisibility(View.INVISIBLE);
        }
        else{
            etTextInput.setVisibility(View.VISIBLE);
            btnSet.setVisibility(View.VISIBLE);
        }
    }
}
