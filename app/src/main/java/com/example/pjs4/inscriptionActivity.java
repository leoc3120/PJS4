package com.example.pjs4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class inscriptionActivity extends AppCompatActivity {

    EditText email,password,name,surname;
    Button btnInscription;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inscription);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        btnInscription = findViewById(R.id.btnInscription);
        progressDialog=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        /*  btnInscription.setOnClickListener(v -> { //ou inscription
            Intent startActivityIntent = new Intent(inscriptionActivity.this, com.example.pjs3.startActivity.class);
            startActivity(startActivityIntent);
        });*/

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth(){

        String mail = email.getText().toString();
        String mdp = password.getText().toString();
        String prenom = name.getText().toString();
        String surname = name.getText().toString();

        if (!mail.matches(emailPattern)){

            email.setError("Entrer un mail correct");
        } else if (mdp.isEmpty() || password.length()<6)
        {
            password.setError("Entrer un mot de passe correct");
        }
        else
        {
            progressDialog.setMessage("Veuillez patientez pendant l'inscription");
            progressDialog.setTitle("Inscription");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(mail,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        /*FirebaseUser user  = mAuth.getCurrentUser();

                        String mail = user.getEmail();
                        String uid = user.getUid();

                        HashMap<Object, String> hashMap = new HashMap<>();

                        hashMap.put("Email", mail);
                        hashMap.put("uid", uid);
                        hashMap.put("prenom", "");
                        hashMap.put("surname", "");
                        hashMap.put("image", "");

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("Users");
                        reference.child(uid).setValue(hashMap);*/
                        progressDialog.dismiss();
                        sendToNextActivity();
                        Toast.makeText(inscriptionActivity.this, "Inscription r??ussie", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(inscriptionActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendToNextActivity() {
        Intent intent=new Intent(inscriptionActivity.this, completeProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void NavigateSignUp(View v) {
        Intent inent = new Intent(this, connexionActivity.class);
        startActivity(inent);
    }

}