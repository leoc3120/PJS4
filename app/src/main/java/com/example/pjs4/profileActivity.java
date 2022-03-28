package com.example.pjs4;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class profileActivity  extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView profileNameTextView, profileHeightTextView, profileWeightTextView, profileSexTextView, profileBirthTextView, profileCountryTextView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ImageView profilePicImageView;
    private FirebaseStorage firebaseStorage;
    Button btnBack;
    private TextView textViewemailname;
    private EditText editTextName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextName = (EditText)findViewById(R.id.et_username);
        profilePicImageView = findViewById(R.id.update_imageView);
        profileNameTextView = findViewById(R.id.profile_name_textView);
        profileHeightTextView = findViewById(R.id.profile_height_textView);
        profileWeightTextView = findViewById(R.id.profile_weight_textView);
        profileSexTextView = findViewById(R.id.profile_sex_textView);
        profileBirthTextView = findViewById(R.id.profile_birth_textView);
        profileCountryTextView = findViewById(R.id.profile_country_textView);
        btnBack = findViewById(R.id.button_back);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();
        // Get the image stored on Firebase via "User id/Images/Profile Pic.jpg".
        storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Using "Picasso" (http://square.github.io/picasso/) after adding the dependency in the Gradle.
                // ".fit().centerInside()" fits the entire image into the specified area.
                // Finally, add "READ" and "WRITE" external storage permissions in the Manifest.
                Picasso.get().load(uri).fit().centerInside().into(profilePicImageView);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack(v);
            }
        });
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), connexionActivity.class));
        }
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Userinformation userProfile = dataSnapshot.getValue(Userinformation.class);
                profileNameTextView.setText(userProfile.getUserName());
                profileHeightTextView.setText(userProfile.getUserHeight());
                profileWeightTextView.setText(userProfile.getUserWeight());
                profileSexTextView.setText(userProfile.getUserSex());
                profileBirthTextView.setText(userProfile.getUserBirth());
                profileCountryTextView.setText(userProfile.getUserCountry());
                textViewemailname = (TextView) findViewById(R.id.textViewEmailAdress);
                textViewemailname.setText(user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateBack(View v) {
        Intent in = new Intent(this, settingsActivity.class);
        startActivity(in);
    }

    public void buttonClickedEditName(View view) {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_name, null);
        final EditText etUsername = alertLayout.findViewById(R.id.et_username);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier le nom");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = etUsername.getText().toString();
                String Height = profileHeightTextView.getText().toString();
                String Weight = profileWeightTextView.getText().toString();
                String Sex = profileSexTextView.getText().toString();
                String Birth = profileBirthTextView.getText().toString();
                String Country = profileCountryTextView.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUsername.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void buttonClickedEditHeight(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_height, null);
        final EditText etUserHeight = alertLayout.findViewById(R.id.et_userHeight);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier la taille");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String Name = profileNameTextView.getText().toString();
                String Height = etUserHeight.getText().toString();
                String Weight = profileWeightTextView.getText().toString();
                String Sex = profileSexTextView.getText().toString();
                String Birth = profileBirthTextView.getText().toString();
                String Country = profileCountryTextView.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUserHeight.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void buttonClickedEditWeight(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_weight, null);
        final EditText etUserWeight = alertLayout.findViewById(R.id.et_userWeight);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier le poids");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = profileNameTextView.getText().toString();
                String Height = profileHeightTextView.getText().toString();
                String Weight = etUserWeight.getText().toString();
                String Sex = profileSexTextView.getText().toString();
                String Birth = profileBirthTextView.getText().toString();
                String Country = profileCountryTextView.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUserWeight.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void buttonClickedEditSex(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_sex, null);
        final EditText etUserSex = alertLayout.findViewById(R.id.et_userSex);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier le sexe");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = profileNameTextView.getText().toString();
                String Height = profileHeightTextView.getText().toString();
                String Weight = profileWeightTextView.getText().toString();
                String Sex = etUserSex.getText().toString();
                String Birth = profileBirthTextView.getText().toString();
                String Country = profileCountryTextView.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUserSex.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void buttonClickedEditBirth(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_birth, null);
        final EditText etUserBirth = alertLayout.findViewById(R.id.et_userBirth);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier la date de naissance ");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = profileNameTextView.getText().toString();
                String Height = profileHeightTextView.getText().toString();
                String Weight = profileWeightTextView.getText().toString();
                String Sex = profileSexTextView.getText().toString();
                String Birth = etUserBirth.getText().toString();
                String Country = profileCountryTextView.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUserBirth.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void buttonClickedEditCountry(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_country, null);
        final EditText etUserCountry = alertLayout.findViewById(R.id.et_userCountry);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Modifier le pays");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = profileNameTextView.getText().toString();
                String Height = profileHeightTextView.getText().toString();
                String Weight = profileWeightTextView.getText().toString();
                String Sex = profileSexTextView.getText().toString();
                String Birth = profileBirthTextView.getText().toString();
                String Country = etUserCountry.getText().toString();
                Userinformation userinformation = new Userinformation(Name, Height, Weight, Sex, Birth, Country);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userinformation);
                databaseReference.child(user.getUid()).setValue(userinformation);
                etUserCountry.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void navigateLogOut(View v) {
        FirebaseAuth.getInstance().signOut();
        Intent inent = new Intent(this, startActivity.class);
        startActivity(inent);
    }





}