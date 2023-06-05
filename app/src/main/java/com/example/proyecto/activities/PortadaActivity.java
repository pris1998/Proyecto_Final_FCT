package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.proyecto.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PortadaActivity extends AppCompatActivity {

    ImageView imageViewIcono;
    ImageView imgVTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Para quitar la barra de notificaciones en la parte superior
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_portada);

        // Agregar animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.top);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.bottom);

        imageViewIcono = findViewById(R.id.imageViewIcono);
        imgVTitulo = findViewById(R.id.imgVTitulo);

        // Asignamos la animación
        imgVTitulo.setAnimation(animacion1);
        imageViewIcono.setAnimation(animacion2);


        // Después de que termine la animación, ir a otra actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Si el usuario ya ha iniciado sesión, ir directamente a la actividad de Paciente
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Si el usuario ha iniciado sesión con Google
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(PortadaActivity.this);
                if (user != null && account != null) {
                    Intent intent = new Intent(PortadaActivity.this,PacienteActivity.class );
                    startActivity(intent);
                    finish();
                }else{
                    // Si el usuario no ha iniciado sesión, ir a la actividad de Login
                    Intent intent = new Intent(PortadaActivity.this,LoginActivity.class );
                    startActivity(intent);
                    finish();
                }

            }
        }, 4000);

    }
}