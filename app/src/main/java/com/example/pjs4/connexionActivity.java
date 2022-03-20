package com.example.pjs4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class connexionActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connexion);
        Button connexion = findViewById(R.id.btnConnexion);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnConnexion);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        connexion.setOnClickListener(v -> {
            Intent homeActivityIntent = new Intent(connexionActivity.this, com.example.pjs4.homeActivity.class);
            startActivity(homeActivityIntent);
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforLogin();
            }
        });

    }

    private void PerforLogin() {

        String mail = email.getText().toString();
        String mdp = password.getText().toString();

        if (!mail.matches(emailPattern)) {

            email.setError("Entrer un mail correct");
        } else if (mdp.isEmpty() || password.length() < 6) {
            password.setError("Entrer un mot de passe correct");
        } else  {
            progressDialog.setMessage("Veuillez patientez pendant la connexion");
            progressDialog.setTitle("Connexion");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(mail, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendToNextActivity();
                        Toast.makeText(connexionActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(connexionActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void sendToNextActivity() {
        Intent intent = new Intent(connexionActivity.this, homeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}