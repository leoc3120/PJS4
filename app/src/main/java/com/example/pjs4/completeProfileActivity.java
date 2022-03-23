package com.example.pjs4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class completeProfileActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText UserName, UserEmail, UserPhone, UserGender, UserBirth;
    ImageView UserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        Intent data = getIntent();
        String Username = data.getStringExtra("Username");
        String Email = data.getStringExtra("Email");
        String Phone = data.getStringExtra("Phone");
        String Gender = data.getStringExtra("Gender");
        String Birth = data.getStringExtra("Birth");
        TextView save = findViewById(R.id.compSave);

        UserName = findViewById(R.id.compUsername);
        UserEmail = findViewById(R.id.compEmail);
        UserPhone = findViewById(R.id.compPhone);
        UserGender = findViewById(R.id.compGender);
        UserBirth = findViewById(R.id.compBirth);
        UserImage = findViewById(R.id.userPhoto);

        UserImage.setOnClickListener((v) -> {

            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(openGalleryIntent, 1000);
        });

        UserEmail.setText(Email);
        UserName.setText(Username);
        UserPhone.setText(Phone);

        Log.d(TAG,"onCreate: " + Username + Email + Phone + Gender + Birth);

        save.setOnClickListener(v -> {
            //verifier
            Intent homeIntent = new Intent(this, homeActivity.class);
            startActivity(homeIntent);
        });


    }
}