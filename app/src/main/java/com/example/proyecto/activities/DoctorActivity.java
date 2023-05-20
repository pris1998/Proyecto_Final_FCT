package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import utils.Adapter;
import utils.DataListPatients;

public class DoctorActivity extends AppCompatActivity {

    //añadir informacion de la coleccin dieta
    FirebaseController fc = new FirebaseController();

    //el boton de add
    FloatingActionButton btnfloating;
    RecyclerView recyclerView;
    List<DataListPatients> dataList;
    //mirar el import a ver si es distinto
    androidx.appcompat.widget.SearchView searchView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        btnfloating = findViewById(R.id.btnfloating);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DoctorActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //llamaa la función para añadir dietas
        //fc.addDieta();
        fc.addUsers();

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        finish();*/

        dataList = new ArrayList<>();

        Adapter adapter = new Adapter(DoctorActivity.this,dataList);
        recyclerView.setAdapter(adapter);

        //Buscador de pacientes
        //searchView.setOnQueryTextListener


        //aqui continua la base de datos que tnego q hacer
        btnfloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esta debe abrir una actividad nueva para añadir cosas
                //Intent intent = new Intent();
            }
        });




    }

    public void searchList(String text){
        ArrayList<DataListPatients> searchList = new ArrayList<>();
        for (DataListPatients dataListPatients : dataList) {
            searchList.add(dataListPatients);
        }
        adapter.searchDataList(searchList);
    }


}