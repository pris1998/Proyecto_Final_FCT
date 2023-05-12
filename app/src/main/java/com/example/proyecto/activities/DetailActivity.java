package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyecto.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailSurname, detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detailName);
        detailSurname = findViewById(R.id.detailSurname);
        detailTitle = findViewById(R.id.detailTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailName.setText(bundle.getString("Name"));
            detailSurname.setText(bundle.getString("Surname"));
            detailTitle.setText(bundle.getString("Title"));

        }

    }
}