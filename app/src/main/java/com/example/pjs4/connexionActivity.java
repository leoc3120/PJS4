package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class connexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Button connexion = findViewById(R.id.btnConnexion);

        connexion.setOnClickListener(v -> {
            Intent homeActivityIntent = new Intent(connexionActivity.this, homeActivity.class);
            startActivity(homeActivityIntent);
        });
    }
}