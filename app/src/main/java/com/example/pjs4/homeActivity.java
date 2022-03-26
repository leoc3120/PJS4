package com.example.pjs4;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //NavigationBarView nav = findViewById(R.id.bottom_navigation);

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


        //caloriesBar.setProgress(70);
        /*nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.btn1:
                        Intent startActivityIntent = new Intent(homeActivity.this, startActivity.class);
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
        });*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
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
}