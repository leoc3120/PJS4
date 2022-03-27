package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class settingsActivity extends AppCompatActivity {

    TextView signIn;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        signIn=findViewById(R.id.lastSignIn);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //signIn.setText((int) mUser.getMetadata().getLastSignInTimestamp());
        int hours   = (int) ((mUser.getMetadata().getLastSignInTimestamp() / (1000*60*60)) % 24);

        int data = (int) mUser.getMetadata().getLastSignInTimestamp();
        long days = TimeUnit.MILLISECONDS.toDays(data);
        data -= TimeUnit.DAYS.toMillis(days);
        System.out.println(days);
        System.out.println(mUser.getMetadata().getLastSignInTimestamp());
        Log.d("data", ((String.valueOf(mUser.getMetadata().getLastSignInTimestamp()))));
        System.out.println(TimeUnit.MILLISECONDS.toDays(mUser.getMetadata().getLastSignInTimestamp()));


        /*NavigationBarView nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.btn1:
                        Intent startActivityIntent = new Intent(settingsActivity.this, startActivity.class);
                        startActivity(startActivityIntent);
                        break;

                    case R.id.btn2:
                        Intent searchActivityIntent = new Intent(settingsActivity.this, searchActivity.class);
                        startActivity(searchActivityIntent);
                        break;

                    case R.id.btn3:
                        Intent profileActivityIntent = new Intent(settingsActivity.this, profileActivity.class);
                        startActivity(profileActivityIntent);
                        break;

                    case R.id.btn4:
                        Intent settingsActivityIntent = new Intent(settingsActivity.this, settingsActivity.class);
                        startActivity(settingsActivityIntent);
                        break;
                    default :
                        break;
                }
                return true;
            }
        });*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btn1:
                                Intent startActivityIntent = new Intent(settingsActivity.this, homeActivity.class);
                                startActivity(startActivityIntent);
                                break;

                            case R.id.btn2:
                                Intent searchActivityIntent = new Intent(settingsActivity.this, searchActivity.class);
                                startActivity(searchActivityIntent);
                                break;

                            case R.id.btn3:
                                Intent profileActivityIntent = new Intent(settingsActivity.this, profileActivity.class);
                                startActivity(profileActivityIntent);
                                break;

                            case R.id.btn4:
                                Intent settingsActivityIntent = new Intent(settingsActivity.this, settingsActivity.class);
                                startActivity(settingsActivityIntent);
                                break;
                            default :
                                break;
                        }
                        return true;
                    }
                });






    }

    public void navigateLogOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent inent = new Intent(this, startActivity.class);
        startActivity(inent);
    }

}