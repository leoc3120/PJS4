package com.example.pjs4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import java.time.Instant;

public class startActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final String TAG = "startActivity";
    //signInButton signInButton;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestScopes(new Scope(Scopes.PLUS_LOGIN))
        .requestEmail()
        .build();

mGoogleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this, this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();

        SignInButton connexion = findViewById(R.id.SignInButton);
        connexion.setOnClickListener(this);
        Button inscription = findViewById(R.id.buttonCreateAccount);

      /*  connexion.setOnClickListener(v -> {
            Intent connexionActivityIntent = new Intent(startActivity.this, connexionActivity.class);
            startActivity(connexionActivityIntent);
        });*/

        inscription.setOnClickListener(v -> {
            Intent inscriptionActivityIntent = new Intent(startActivity.this, inscriptionActivity.class);
            startActivity(inscriptionActivityIntent);
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SignInButton:
                signIn();
                break;
            case R.id.btnInscription:
                break;
        }
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
     public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            handleSignInResult(result);
        }
     }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult " + result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();

            Intent connexionActivityIntent = new Intent(startActivity.this, homeActivity.class);
            startActivity(connexionActivityIntent);
        } else {

        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("TAG", "onConnectionFailed" + connectionResult);
    }
}