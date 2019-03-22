package com.example.scar.gym.Vistas.Usuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scar.gym.BD.UsuarioDataSource;
import com.example.scar.gym.Datos.CaracteristicasUsuario;
import com.example.scar.gym.Datos.UsuarioRutinaDieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.Log_Registro.LogInActivity;
import com.example.scar.gym.Vistas.Log_Registro.RegistroActivity;
import com.example.scar.gym.Vistas.MainActivity;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class UsuarioActivity extends AppCompatActivity {

    private Button logout,nuevaMarca;
    private UsuarioRutinaDieta uRD;
    private TableLayout tabla;
    List<CaracteristicasUsuario> carac;
    private TextView edad,altura,peso;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        uRD= getIntent().getExtras().getParcelable("user");
        tabla= findViewById(R.id.tabla);
        tabla= findViewById(R.id.tabla);
        carac = getCaract(uRD);
        addRow(carac);
        logout = findViewById(R.id.btn_logout);
        nuevaMarca = findViewById(R.id.btnAddDatos);
        edad=findViewById(R.id.input_edad);
        peso = findViewById(R.id.input_peso);
        altura = findViewById(R.id.input_altura);
        settings=getSharedPreferences(LogInActivity.nombrePreferencias,MODE_PRIVATE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePreferences();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        nuevaMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addNuevaMarca();
            }
        });
    }
    private void removePreferences(){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario","null");
        editor.putString("pass","null");
        editor.commit();
    }

    private void addNuevaMarca(){
        if(checkNotEmpty() && checkCorrect()){
            UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
            uDS.open();

            int iEdad = Integer.parseInt(edad.getText().toString());
            int iAltura = Integer.parseInt(altura.getText().toString());
            double dPeso = Double.parseDouble(peso.getText().toString());

            uDS.createEdadAlturaPeso(uRD.getId_usuario(),iEdad,dPeso,iAltura,new Date());
            uDS.close();
            CaracteristicasUsuario carac = new CaracteristicasUsuario(iAltura,dPeso,iEdad,new Date());
            List<CaracteristicasUsuario> c = new ArrayList<>();
            c.add(carac);
            addRow(c);
            peso.setText("");
            altura.setText("");
            edad.setText("");
            Toast.makeText(getApplicationContext(),"Nueva Marca Añadida",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
    }

    private List<CaracteristicasUsuario> getCaract(UsuarioRutinaDieta uRD){
        UsuarioDataSource uDS = new UsuarioDataSource(getApplicationContext());
        uDS.open();
        List<CaracteristicasUsuario> carac = null;
        try {
            carac = uDS.getHistorialUsuario(uRD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        uDS.close();
        return carac;
    }
    private void addRow(List<CaracteristicasUsuario> carac){
        for(CaracteristicasUsuario c : carac){
            TableRow row = new TableRow(getApplicationContext());
            TextView edad = new TextView(getApplicationContext());
            edad.setText(String.valueOf(c.getEdad()));

            TextView altura = new TextView(getApplicationContext());
            altura.setText(String.valueOf(c.getAltura()));

            TextView peso = new TextView(getApplicationContext());
            peso.setText(String.valueOf(c.getPeso()));

            TextView fecha = new TextView(getApplicationContext());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = c.getFecha();
            String sDate = formatter.format(date);
            fecha.setText(sDate);

            Double alturaDouble = (double)c.getAltura();
            double alturaMetros = alturaDouble/100;
            double imc = c.getPeso()/(alturaMetros*alturaMetros);
            double scale = Math.pow(10,2);
            Double res =Math.round(imc*scale)/scale;

            TextView IMC = new TextView(getApplicationContext());
            IMC.setText(Double.toString(res));

            row.addView(edad);
            row.addView(peso);
            row.addView(altura);
            row.addView(IMC);
            row.addView(fecha);
            tabla.addView(row);
        }
    }

    private boolean checkNotEmpty(){
        if(edad.getText().toString().isEmpty() || altura.getText().toString().isEmpty() || peso.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show();
            return  false;
        }
        else
            return true;
    }

    private boolean checkCorrect(){
        if(Integer.parseInt(edad.getText().toString()) < 0 || Integer.parseInt(altura.getText().toString()) < 100 ||
                Integer.parseInt(altura.getText().toString()) > 220 || Double.parseDouble(peso.getText().toString()) < 40){
            Toast.makeText(getApplicationContext(), "Algun valor es incorrecto", Toast.LENGTH_LONG).show();
            return  false;
        }
        else
            return true;
    }


    }


