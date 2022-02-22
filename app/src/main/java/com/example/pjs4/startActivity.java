package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button connexion = findViewById(R.id.buttonConnect);
        Button inscription = findViewById(R.id.buttonCreateAccount);

        connexion.setOnClickListener(v -> {
            Intent connexionActivityIntent = new Intent(startActivity.this, connexionActivity.class);
            startActivity(connexionActivityIntent);
        });

        inscription.setOnClickListener(v -> {
            Intent inscriptionActivityIntent = new Intent(startActivity.this, inscriptionActivity.class);
            startActivity(inscriptionActivityIntent);
        });
    }
}