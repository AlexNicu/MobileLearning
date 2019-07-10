package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TitleScreen extends AppCompatActivity {
Button next2,back2;
TextView tv;
EditText et;
DatabaseReference mDatabaseReference;
String name, subdomain, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        next2= findViewById(R.id.Next2);
        back2=findViewById(R.id.Back2);
        tv=findViewById(R.id.titleread);
        et=findViewById(R.id.titlewrite);

        SharedPreferences sharedpref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        name = sharedpref2.getString("username", "nu-merge");
        SharedPreferences sharedpref3 = getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
        subdomain = sharedpref3.getString("subfilename", "nu-merge");
        subject = sharedpref3.getString("filename", "Nu-merge");

        mDatabaseReference=FirebaseDatabase.getInstance().getReference();

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayText=et.getText().toString();
                SharedPreferences sharedpref3=getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3=sharedpref3.edit();
                editor3.putString("title",et.getText().toString());
                editor3.apply();
                if(displayText.isEmpty()){
                    et.setError("Text Required");
                    et.requestFocus();
                    return;
                }else {
                    //TODO subdomain salveaza Macroeconomie indiferent de subiectul selectat si nu inteleg de si-l ia, fiindca am sters orice instanta a cuvantului
                    //Microeconomie din aplicatie
                    mDatabaseReference.child("Titles/" + subject + "/" + subdomain + "/" + name + "/").push().setValue(displayText);
                    startActivity(new Intent(TitleScreen.this, UploadActivity.class));

                }

            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleScreen.this, UplArticles.class));
            }
        });



    }



}
