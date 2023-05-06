package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.proyecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PortadaActivity extends AppCompatActivity {

    ImageView imageViewIcono;
    ImageView imgVTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Para quitar la flanga de la parte de arriba
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_portada);

        //Agregar animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.top);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.bottom);

        imageViewIcono = findViewById(R.id.imageViewIcono);
        imgVTitulo = findViewById(R.id.imgVTitulo);

        //asignamos la animacion
        imgVTitulo.setAnimation(animacion1);
        imageViewIcono.setAnimation(animacion2);


        //despues de salir la animacion se vaya a otra Actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Si el usuario ya inicio sesion se dirige directamente al ChooseUser
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(PortadaActivity.this,ChooseUserActivity.class );
                    startActivity(intent);
                    finish();
                }else{
                    //Si aun no ha iniciado sesion se dirige al Login
                    Intent intent = new Intent(PortadaActivity.this,LoginActivity.class );
                    startActivity(intent);
                    finish();
                }

            }
        }, 4000);

    }
}