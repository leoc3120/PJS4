package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btn1:
                                Intent startActivityIntent = new Intent(profileActivity.this, homeActivity.class);
                                startActivity(startActivityIntent);
                                break;

                            case R.id.btn2:
                                Intent searchActivityIntent = new Intent(profileActivity.this, searchActivity.class);
                                startActivity(searchActivityIntent);
                                break;

                            case R.id.btn3:
                                Intent profileActivityIntent = new Intent(profileActivity.this, profileActivity.class);
                                startActivity(profileActivityIntent);
                                break;

                            case R.id.btn4:
                                Intent settingsActivityIntent = new Intent(profileActivity.this, settingsActivity.class);
                                startActivity(settingsActivityIntent);
                                break;
                            default :
                                break;
                        }
                        return true;
                    }
                });
    }
}