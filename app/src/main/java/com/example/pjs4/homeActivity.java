package com.example.pjs4;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;

//map dependencies


public class homeActivity extends AppCompatActivity {

    private int progress = 0;
    Button buttonIncrement;
    Button buttonDecrement;
    Button buttonReset;
    ProgressBar progressBar;
    TextView textView;
    CheckBox handisport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        buttonDecrement = (Button) findViewById(R.id.button_decr);
        buttonIncrement = (Button) findViewById(R.id.button_incr);
        buttonReset = findViewById(R.id.button_reset);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.text_view_progress);
        handisport = (CheckBox) findViewById(R.id.simpleCheckBox);

        Button btnSearchMaps = findViewById(R.id.btnSearchMap);
        NavigationBarView nav = findViewById(R.id.bottom_navigation);

        btnSearchMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions(homeActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    Toast.makeText(homeActivity.this, "Impossible d'acc??der ?? la g??olocalisation", Toast.LENGTH_SHORT).show();
                    return;
                }
                double longitude = location.getLongitude();

                double latitude = location.getLatitude();
                StringBuilder sb = new StringBuilder();
                sb.append("geo:");
                sb.append(latitude+",");
                sb.append(longitude+"?");
                sb.append("z=10&q=salle+de+sport");
                if (handisport.isChecked()) {
                    sb.append("+handicap");
                }
                String query = sb.toString();

                try {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse(query);
                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    // Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

                    // Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }

                catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(homeActivity.this, "Impossible de lancer Google Maps", Toast.LENGTH_SHORT).show();
                }


            }
        });
        // when clicked on buttonIncrement progress in increased by 10%
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if progress is less than or equal
                // to 90% then only it can be increased
                if (progress <= 2600) {
                    progress += 100;
                    updateProgressBar();
                }
            }
        });

        // when clicked on buttonIncrement progress in decreased by 10%
        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If progress is greater than
                // 10% then only it can be decreased
                if (progress >= 100) {
                    progress -= 100;
                    updateProgressBar();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress=0;
                updateProgressBar();
            }
        });
        OnCompleteListener<AuthResult> completeListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                    System.out.println(isNew);
                    Log.d("MyTAG", "onComplete: " + (isNew ? "new user" : "old user"));
                }
            }
        };


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.btn1:
                        Intent startActivityIntent = new Intent(homeActivity.this, homeActivity.class);
                        startActivity(startActivityIntent);
                        break;

                    case R.id.btn2:
                        Intent searchActivityIntent = new Intent(homeActivity.this, searchActivity.class);
                        startActivity(searchActivityIntent);
                        break;

                    case R.id.btn3:
                        Intent profileActivityIntent = new Intent(homeActivity.this, profileActivity.class);
                        startActivity(profileActivityIntent);
                        break;

                    case R.id.btn4:
                        Intent settingsActivityIntent = new Intent(homeActivity.this, settingsActivity.class);
                        startActivity(settingsActivityIntent);
                        break;
                    default :
                        break;
                }
                return true;
            }
        });
    }

    // updateProgressBar() method sets
    // the progress of ProgressBar in text
    private void updateProgressBar() {
        progressBar.setProgress(progress);
        textView.setText(String.valueOf(progress));
    }

}