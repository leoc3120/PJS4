package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pjs4.repository.APILoader;

public class searchActivity extends AppCompatActivity {

    Button boutonR;

    APILoader access = new APILoader();
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back = findViewById(R.id.button_back);

        boutonR = findViewById(R.id.boutonR);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack(v);
            }
        });

        boutonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView label = (TextView) findViewById(R.id.label);
                final TextView diet = (TextView) findViewById(R.id.diet);
                final ImageView img = (ImageView) findViewById(R.id.imgRecette);
                final Button btn = (Button) findViewById(R.id.ouvrir);
                final Button site = (Button) findViewById(R.id.GoSite);

                final EditText entree = (EditText) findViewById(R.id.entree);

                // essayer d'ajouter i ici
                access.connexion(label, diet, img, btn, entree, site);
               // mTextView.setText(access.);
            }
        });
    }

    public void navigateBack(View v){
        Intent inent = new Intent(this, homeActivity.class);
        startActivity(inent);
    }
}