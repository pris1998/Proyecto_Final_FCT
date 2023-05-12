package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.activities.doctores.DietaActivity;
import com.google.android.material.navigation.NavigationView;

public class InformationPatient extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nav;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_patient);

        drawerLayout = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Esta parte sería con metodos a parte para cada celda del menu
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                    {
                        Toast.makeText(InformationPatient.this, "Ha selecionado HOME", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.logout:
                    {
                        Toast.makeText(InformationPatient.this, "Salir de la sesión", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.vaccine:
                    {
                        Intent intent = new Intent(InformationPatient.this, DietaActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}