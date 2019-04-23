package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mAuth=FirebaseAuth.getInstance();

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        progressBar=(ProgressBar)findViewById(R.id.pb);

        findViewById(R.id.tvSignup).setOnClickListener(this);
        findViewById(R.id.btLogin).setOnClickListener(this);

        etEmail.setText(sharedPreferences.getString(KEY_EMAIL,""));
        etPassword.setText(sharedPreferences.getString(KEY_PASS,""));
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);


    }

    private void userLogin(){
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

       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               progressBar.setVisibility(View.GONE);
               if(task.isSuccessful()){
               Intent intent=new Intent(MainActivity.this,UploadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
               }
               else{
                   Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
               }
           }
       });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tvSignup:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.btLogin:
                userLogin();
                break;

        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable s) {
      //  managePrefs();
    }


    private void managePrefs(){
        if(etEmail!=null && etPassword!=null){
            editor.putString(KEY_EMAIL, etEmail.getText().toString().trim());
            editor.putString(KEY_PASS, etPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_EMAIL);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }


}
