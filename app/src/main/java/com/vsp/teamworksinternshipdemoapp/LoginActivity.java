package com.vsp.teamworksinternshipdemoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    private static final String SHARED_PEFS = "sharedPreds";
    private static final String TAG = "Hello";
    private EditText mEmailField;
    private EditText mPasswordField, motpfield;
    private Button mLoginBtn,otpverifybtn,resendotp;
    private EditText mFieldID;
    String sendname;
    private TextView timertextview;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    CountDownTimer countDownTimer;
    private FirebaseAuth mAuth,mAuthmobile;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference database, clientcheck;
    public int error = 0,otpcheck=0;
    private String codeSent="";

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    private int startSignIn() {

        error = 0;
        final String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            if (TextUtils.isEmpty(email))
                mEmailField.setError("Enter mail address");
            else
                mPasswordField.setError("Enter Password");
            error++;

        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        error++;
                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    } else {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);


                    }
                }
            });
        }
        if (error == 0)
            return 0;
        else
            return 1;

    }

    public void signup_click(View view) {
        Intent intent = new Intent(LoginActivity.this, Signupform.class);
        startActivity(intent);
    }

    private void toast(String x) {
        Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();
    }

}
