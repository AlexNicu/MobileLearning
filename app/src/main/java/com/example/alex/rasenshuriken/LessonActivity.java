package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LessonActivity extends AppCompatActivity {

    TextView titleview;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        titleview=findViewById(R.id.LessonTitle);
        textview=findViewById(R.id.TextLesson);



        Intent intent=getIntent();
        String title=intent.getStringExtra(ListArticles.LESSON_NAME);
        String text=intent.getStringExtra(ListArticles.TEXT);
        titleview.setText(title);
        textview.setText(text);


    }




}
