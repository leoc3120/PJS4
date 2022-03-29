package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
                final Button site = (Button) findViewById(R.id.GoSite);
                final TextView url = (TextView) findViewById(R.id.url);
                final EditText entree = (EditText) findViewById(R.id.entree);


                access.connexion(label, diet, img, btn, entree, site, url);

                site.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String lien = url.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(lien));
                        startActivity(intent);
                    }
                });
            }
        });
    }
}