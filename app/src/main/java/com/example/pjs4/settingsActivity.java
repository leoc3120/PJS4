package com.example.pjs4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class settingsActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    TextView signIn;
    TextView Name;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        signIn=findViewById(R.id.lastSignIn);
        back = findViewById(R.id.button_back);
        Name = findViewById(R.id.name);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(mUser.getMetadata().getLastSignInTimestamp()));
        System.out.println(dateString);
        signIn.setText("Dernière connexion: "+ dateString);
        System.out.println(mUser.getMetadata().getLastSignInTimestamp());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack(v);
            }
        });

        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Userinformation userProfile = dataSnapshot.getValue(Userinformation.class);
                Name.setText(userProfile.getUserName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(settingsActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

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



    }


    public void navigateContactPage(View v){
        Intent inent = new Intent(this, contactActivity.class);
        startActivity(inent);
    }


    public void navigateLogOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent inent = new Intent(this, startActivity.class);
        startActivity(inent);
    }

    public void navigateBack(View v){
        Intent inent = new Intent(this, homeActivity.class);
        startActivity(inent);
    }

    public void navigateToProfil(View v){
        Intent inent = new Intent(this, profileActivity.class);
        startActivity(inent);
    }

    public void showDialog(View v){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Signaler un probleme");
        dialog.setMessage("Donnez-nous votre avis");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.activity_feedback, null);
        final MaterialEditText edtEmail = reg_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtName = reg_layout.findViewById(R.id.edtName);
        final MaterialEditText edtFeedback = reg_layout.findViewById(R.id.edtFeedback);

        dialog.setView(reg_layout);

        // set

        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Toast.makeText(settingsActivity.this, "Veuillez taper votre email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtName.getText().toString())) {
                    Toast.makeText(settingsActivity.this, "Veuillez taper votre nom", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtFeedback.getText().toString())) {
                    Toast.makeText(settingsActivity.this, "le champ commentaire ne peut pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseUser user = firebaseAuth.getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("Feedback");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object value = snapshot.getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(settingsActivity.this, "Failed to read value", Toast.LENGTH_SHORT).show();
                    }
                });

                myRef.child(user.getUid()).setValue(edtName.getText().toString());
                myRef.child(user.getUid()).child(edtName.getText().toString()).setValue(edtFeedback.getText().toString());

                //myRef.child(user.getUid()).setValue(edtName.getText().toString());
                //myRef.child(user.getUid()).setValue(edtFeedback.getText().toString());

                Toast.makeText(settingsActivity.this, "Merci pour votre commentaire", Toast.LENGTH_SHORT).show();

            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }

    public void navigateToShare(View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi,I recommend this app for you.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }


}