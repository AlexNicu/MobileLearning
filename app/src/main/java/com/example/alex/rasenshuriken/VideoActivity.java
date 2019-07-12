package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


    }
/*
    public void btnLink(View v){
        Intent i = new Intent(VideoActivity.this,LinkUpload.class);
        startActivity(i);
    }
    */
    public void btnVideo(View v){
        Intent i = new Intent(VideoActivity.this,VideoUpload.class);
        startActivity(i);
    }
}
