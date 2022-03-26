package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
                final TextView label = (TextView) findViewById(R.id.label);
                final TextView diet = (TextView) findViewById(R.id.diet);
                final ImageView img = (ImageView) findViewById(R.id.imgRecette);
                final Button btn = (Button) findViewById(R.id.ouvrir);

                final EditText entree = (EditText) findViewById(R.id.entree);

                access.connexion(label, diet, img, btn, entree);
               // mTextView.setText(access.);
            }
        });
    }
}