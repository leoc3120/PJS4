package com.example.pjs4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;

public class completeProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = completeProfileActivity.class.getSimpleName();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button btnsave;
    private FirebaseAuth firebaseAuth;
    private TextView textViewemailname;
    private DatabaseReference databaseReference;
    private EditText editTextName;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextSex;
    private EditText editTextBirth;
    private EditText editTextCountry;
    private ImageView profileImageView;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;

    public completeProfileActivity() {
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_complete_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),connexionActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextName = (EditText)findViewById(R.id.EditTextName);
        editTextHeight = (EditText)findViewById(R.id.EditTextHeight);
        editTextWeight = (EditText)findViewById(R.id.EditTextWeight);
        editTextSex = (EditText)findViewById(R.id.EditTextSex);
        editTextBirth = (EditText)findViewById(R.id.EditTextBirth);
        editTextCountry = (EditText)findViewById(R.id.EditTextCountry);
        btnsave=(Button)findViewById(R.id.btnSaveButton);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        btnsave.setOnClickListener(this);
        textViewemailname=(TextView)findViewById(R.id.textViewEmailAdress);
        textViewemailname.setText(user.getEmail());
        profileImageView = findViewById(R.id.update_imageView);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent();
                profileIntent.setType("image/*");
                profileIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(profileIntent, "Select Image."), PICK_IMAGE);
            }
        });

        editTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        completeProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                editTextBirth.setText(date);
            }
        };



    }
    private void userInformation(){
        String Name = editTextName.getText().toString().trim();
        String Height = editTextHeight.getText().toString().trim();
        String Weight = editTextWeight.getText().toString().trim();
        String Sex = editTextSex.getText().toString().trim();
        String Birth = editTextBirth.getText().toString().trim();
        String Country = editTextCountry.getText().toString().trim();
        Userinformation userinformation = new Userinformation(Name,Height,Weight,Sex,Birth,Country);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userinformation);
        Toast.makeText(getApplicationContext(),"User information updated",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnsave){
            if (imagePath == null) {

                Drawable drawable = this.getResources().getDrawable(R.drawable.defavatar);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.defavatar);
                // openSelectProfilePictureDialog();
                userInformation();
                // sendUserData();
                finish();
                startActivity(new Intent(completeProfileActivity.this, homeActivity.class));
            }
            else {
                userInformation();
                sendUserData();
                finish();
                startActivity(new Intent(completeProfileActivity.this, homeActivity.class));
            }
        }
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get "User UID" from Firebase > Authentification > Users.
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic"); //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(completeProfileActivity.this, "Error: Uploading profile picture", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(completeProfileActivity.this, "Profile picture uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openSelectProfilePictureDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        TextView title = new TextView(this);
        title.setText("Profile Picture");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);
        TextView msg = new TextView(this);
        msg.setText("Please select a profile picture \n Tap the sample avatar logo");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });
        new Dialog(getApplicationContext());
        alertDialog.show();
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);
    }
}