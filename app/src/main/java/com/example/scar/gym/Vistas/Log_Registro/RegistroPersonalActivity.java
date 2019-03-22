    package com.example.scar.gym.Vistas.Log_Registro;

    import android.graphics.Color;
    import android.graphics.drawable.Drawable;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.app.ActivityOptions;
    import android.content.Intent;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.scar.gym.Datos.Usuario;
    import com.example.scar.gym.R;

    public class RegistroPersonalActivity extends AppCompatActivity {


            private TextView txNombre,txApellidos;
            private Button siguiente;
            private Usuario newUser;



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_registro_personal);
                newUser=getIntent().getExtras().getParcelable("user");
                txNombre=findViewById(R.id.input_nombre);
                txApellidos=findViewById(R.id.input_apellidos);
                siguiente=findViewById(R.id.btnSiguiente);

                Drawable draw = ContextCompat.getDrawable(this, R.drawable.custom_button);
                siguiente.setBackground(draw);
                siguiente.setTextColor(Color.WHITE);


            }

            public void nextActivityPersonal(View view){
                if(checkNotEmpty()){
                    newUser.setNombre(txNombre.getText().toString());
                    newUser.setApellidos(txApellidos.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),RegistroDatosActivity.class);
                    intent.putExtra("user",newUser);
                    startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                }
            }

            private boolean checkNotEmpty(){
                String nombre = txNombre.getText().toString();
                String apellidos = txApellidos.getText().toString();
                if(nombre.isEmpty() || apellidos.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Algun campo esta vacio", Toast.LENGTH_LONG).show();
                    return false;
                }
                else
                    return true;
            }


        }


