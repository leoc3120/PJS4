package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Button inscription = findViewById(R.id.btnInscription);

        inscription.setOnClickListener(v -> {
            Intent startActivityIntent = new Intent(inscriptionActivity.this, startActivity.class);
            startActivity(startActivityIntent);
        });
    }
}