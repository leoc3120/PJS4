package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class completeProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        TextView save = findViewById(R.id.compSave);

        save.setOnClickListener(v -> {
            //verifier
            Intent homeIntent = new Intent(this, homeActivity.class);
            startActivity(homeIntent);
        });
    }
}