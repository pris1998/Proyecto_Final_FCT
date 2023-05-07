package com.example.proyecto.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    //Todo. Lo he creado statico para poder llamarlo en otras actividades
    //Todo. en vez de crear todo el tiempo uno nuevo (se llama RegisterActivity)
    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    //TextViews
    private TextInputEditText txtEmailUser;
    private TextInputEditText txtPasswordUser;
    TextView txtOlvidarPassword;

    Button btnInicioSesion;
    Button btnRegistro;

    //Coge el usuario usado
    public static FirebaseUser user ;
    String email = "";
    String password = "";


    //Variables de google
    public SignInButton signInButton;
    public GoogleSignInClient signInClient;
    public static final int SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializacion de las variables
        txtEmailUser =  findViewById(R.id.txtEmailUser);
        btnInicioSesion = (Button) findViewById(R.id.btnEntrar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtPasswordUser = findViewById(R.id.txtPasswordUser);

        txtOlvidarPassword = findViewById(R.id.txtOlvidarPassword);

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //No esta verificado te reedirige a la pagina del Registro para registrar el usuario
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class );
                startActivity(intent);
            }
        });

        //Google configuration
        signInButton = findViewById(R.id.loginGoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });



        //Olvidaste contraseña
        txtOlvidarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void myToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    //parte nueva
    public void validate() {
        email = txtEmailUser.getText().toString().trim();
        password = txtPasswordUser.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmailUser.setError("Correo inválido");
            return;
        } else {
            txtEmailUser.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            txtPasswordUser.setError("Se necesitan 8 caracteres");
            return;
            //tiene q contener unnúmero la contraseña pero no me funciona asi q nada
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            txtPasswordUser.setError("Introduzca algún número");
            return;
        }else{
            txtPasswordUser.setError(null);
        }

        iniciarSesion(email,password);
    }

    public void iniciarSesion(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this,ChooseUserActivity.class );
                            startActivity(intent);
                            finish();
                        }else{
                            myToast("Las credenciales no son correctas, inténtelo de nuevo");
                        }
                    }
                });
    }

    private ActivityResultLauncher<Intent> resultGoogleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        if (account != null) {
                            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                    .addOnCompleteListener(this, task1 -> {
                                        if (task1.isSuccessful()) {
                                            Intent intent = new Intent(this, ChooseUserActivity.class);
                                            startActivity(intent);
                                        } else {
                                            myToast( "No se pudo iniciar sesión con Google.");
                                        }
                                    })
                                    .addOnFailureListener(this, e -> {
                                        myToast( "No se pudo iniciar sesión con Google.");
                                    });
                        }
                    } catch (ApiException e) {
                        myToast( "No se pudo iniciar sesión con Google.");
                    }
                }
            }
    );


    public void signInGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_usuario_id))
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(LoginActivity.this,gso);
        signInClient.signOut();

        resultGoogleLauncher.launch(signInClient.getSignInIntent());
    }
    }

