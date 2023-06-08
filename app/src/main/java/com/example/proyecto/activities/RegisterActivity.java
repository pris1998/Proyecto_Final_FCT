package com.example.proyecto.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    TextView  txtnewUser;

    TextInputEditText txtEEmail, txtEPassword, confirmarPassword;


    Button btnInicioS;

    String email ;
    String password;
    String confirmPassword;

    private FirebaseAuth Fauth = FirebaseAuth.getInstance();


    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEEmail = findViewById(R.id.txtEEmail);
        txtEPassword = findViewById(R.id.txtEPassword);
        confirmarPassword = findViewById(R.id.confirmarPassword);

        btnInicioS = findViewById(R.id.btnInicioS);
        txtnewUser = findViewById(R.id.txtnewUser);

        txtnewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al pulsar el texto al inicio
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class );
                startActivity(intent);
            }
        });

        btnInicioS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void myToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    /**
     * Método para validar los datos de registro.
     */
    public void validate() {

        email = txtEEmail.getText().toString().trim();
        password = txtEPassword.getText().toString().trim();
        confirmPassword = confirmarPassword.getText().toString().trim();

        // Validar el formato del correo electrónico
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEEmail.setError("Correo invalido");
            return;
        } else {
            txtEEmail.setError(null);
        }
        // Validar la longitud y el contenido de la contraseña
        if (password.isEmpty() || password.length() < 8) {
            txtEPassword.setError("Se necesitan 8 caracteres");
            return;
            //tiene q contener unnúmero la contraseña pero no me funciona asi q nada
            //si no es la contraseña se manda como nulo
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            txtEPassword.setError("Introduzca algún número");
            return;
        } else {
            txtEPassword.setError(null);
        }

        // Validar que la confirmación de contraseña sea igual a la contraseña
        if (!confirmPassword.equals(password)) {
                confirmarPassword.setError("Deben ser iguales");
                return;
        } else {
            registroBD(email, password);
            registro(email, password);
        }
    }
    /**
     * Método para registrar al usuario en Firebase Authentication.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public void registro(String email,String password){
        Fauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this,PacienteActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            myToast("Error al registrarse");
                        }
                    }
                });
    }


    /**
     * Método para registrar al usuario en la base de datos de Firebase Firestore.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public void registroBD( String email,String password){
        //Uso un HashMap porque es más facil ya que tiene un clave por (String)
        // y un valor (Object) son los objetos que se guardan al ecribirlo mediante la pantalla de Registro
        Map<String,Object> mapDatos = new HashMap<>();
        mapDatos.put("email",email);
        mapDatos.put("password",password);
        firebaseFirestore.collection("users").add(mapDatos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // Éxito en la creación del usuario en la base de datos
                myToast("Usuario creado en BD");
                // Finaliza la actividad
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                myToast("Error al registrar usuario");
            }
        });
    }


    /**
     * Método que se llama cuando se presiona el botón de retroceso.
     * Regresa a la actividad de inicio de sesión.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}