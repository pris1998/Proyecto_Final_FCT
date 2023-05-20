package com.example.proyecto.activities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proyecto.activities.doctores.Alimentos;
import com.example.proyecto.activities.doctores.Dieta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FirebaseController {

    FirebaseFirestore  firebaseFirestore= FirebaseFirestore.getInstance();
    CollectionReference usuarios = firebaseFirestore.collection("users");

    //pasar de un documento
    CollectionReference dietas = firebaseFirestore.collection("users").document("HMpzzDqpNiDMLS60kkdY").collection("doctores");//Cambiar a dieta cuando lo tenga

    ArrayList<Alimentos> alimentos = new ArrayList<Alimentos>();


    public void addDieta(){
        Map<String,Object> dato1 = new HashMap<>();
        Dieta dieta;
        alimentos.add(new Alimentos("tipo","pan"));
        dieta = new Dieta("Dieta1", alimentos);
        dato1.put("Dieta1", Arrays.asList());
        dietas.add(dieta).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("Olee","Oleee");
                }else{
                    Log.d("Nooooo", "Roto");
                }
            }
        });
    }

    public void getDieta(){
        DocumentReference doctRef = firebaseFirestore.collection("Dietas").document("Tipo_Dietas");
        doctRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Dieta dieta = documentSnapshot.toObject(Dieta.class);
            }
        });
    }

    public void addUsers(){
        User user = new User("Rafael","", "pass");
        //si los usuarios están vacíos

        usuarios.add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.d("Olee","Oleee");
                }else{
                    Log.d("Nooooo", "Roto");
                }
            }
        });
    }

    public void getUsers(){
        DocumentReference doctRef = firebaseFirestore.collection("Usuarios").document("Tipos_usuarios");
        doctRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
            }
        });
    }
    //Preguntaaaaaa :
    //aconsejas Map o Array??
    public void postUsers(){
        usuarios.add(new User("Rafael","", "pass")).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //Salga bien
                Log.d("Error", "Usuario creado");
                //finaliza la activity
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error", "Usuario da fallo");
            }
        });
    }


}
