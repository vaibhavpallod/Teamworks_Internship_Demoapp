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

//import com.CA.documents.directcilentview.MainActivity2;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Firebase.setAndroidContext(this);
        sharedPreferences = getSharedPreferences("SaveData", MODE_PRIVATE);
        editor = sharedPreferences.edit();
      /*  editor.putBoolean("case2", false);
        editor.putBoolean("case1", false);*/
        editor.apply();
        getSupportActionBar().setTitle("");
        getWindow().setStatusBarColor(Color.parseColor("#06023b"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#06023b")));


        mLoginBtn = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        mAuthmobile= FirebaseAuth.getInstance();
        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText) findViewById(R.id.passwordField);
        database = FirebaseDatabase.getInstance().getReference();
        clientcheck = FirebaseDatabase.getInstance().getReference();
        otpverifybtn= findViewById(R.id.otpverifybtn);

        /*instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Instruction.class);
                startActivity(intent);
            }
        });*/

       /* resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
//                otpverifybtn.setVisibility(View.GONE);
                resendotp.setVisibility(View.GONE);
            }
        });
        countDownTimer=new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timertextview.setText("resend otp in "+ (int) (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                otpverifybtn.setVisibility(View.VISIBLE);
                mLoginBtn.setVisibility(View.GONE);
                timertextview.setVisibility(View.GONE);
                resendotp.setVisibility(View.VISIBLE);
            }
        };
*/
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });
       /* otpverifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!motpfield.getText().toString().equals("")){
                    countDownTimer.cancel();
                    verifycode();

                }
                else
                    toast("enter OTP first");
            }
        });*/

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);

                    boolean case11 = sharedPreferences.getBoolean("case1", false);
//                    boolean case12 = sharedPreferences.getBoolean("case2", false);

                    if (case11) {

                    } /*else if (case12) {
                        String name = sharedPreferences.getString("sendname1", "");
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        intent.putExtra("sendnameid", name);
                        startActivity(intent);


                    }*/
                }
            }
        };


    }

  /*  private void sendcode(String key) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+key,60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
        toast("Otp sent successfully");
    }
    private void verifycode(){
        String code = motpfield.getText().toString();

        if(!codeSent.equals("")){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent,code);
        signInWithPhoneAuthCredential(credential);
        }
        else {
            toast("code not sent");
        }
    }*/

  /*  private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuthmobile.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Mytask mytask = new Mytask();
                            mytask.execute();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                toast("failed otp verification try to resend");
                countDownTimer.start();
//                resendotp.setVisibility(View.VISIBLE);
            }
        });
    }
*/

  /*  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };
*/

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
