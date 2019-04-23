package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        progressBar=(ProgressBar)findViewById(R.id.pb);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btSignUp).setOnClickListener(this);

    }

    private void registerUser(){
        String email= etEmail.getText().toString().trim();
        String password= etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError(" The e-mail address is necessary");
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("The provided e-mail address is invalid");
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError("The Password is necessary");
            etPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            etPassword.setError("The minimum size of the password must be 6 ");
            etPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                   Toast.makeText(getApplicationContext(),"User Registration has ended succesfully", Toast.LENGTH_SHORT).show();
              startActivity(new Intent(SignUpActivity.this,UserProfile.class));
                }else{
                   if(task.getException() instanceof FirebaseAuthUserCollisionException){
                       Toast.makeText(getApplicationContext(),"The provided e-mail is already registered", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btSignUp:
                registerUser();
                break;
            case R.id.tvSignup:
                startActivity(new Intent(this,MainActivity.class));
                break;

        }
    }
}
