package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View view  = findViewById(R.id.page_4);

        view.setOnClickListener(v -> {
            Intent homeActivityIntent = new Intent(this, settingsActivity.class);
            startActivity(homeActivityIntent);
        });
    }

}