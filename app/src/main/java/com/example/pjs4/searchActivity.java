package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pjs4.repository.PlaceholderPost;

public class searchActivity extends AppCompatActivity {

    Button boutonR;

    PlaceholderPost access = new PlaceholderPost();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        boutonR = findViewById(R.id.boutonR);

        boutonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                access.connexion();
                final TextView mTextView = (TextView) findViewById(R.id.texte2);
                mTextView.setText(access.connexion());
            }
        });
    }
}