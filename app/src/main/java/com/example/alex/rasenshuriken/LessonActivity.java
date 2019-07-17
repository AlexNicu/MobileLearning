package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LessonActivity extends AppCompatActivity {

    TextView titleview;
    TextView textview, testView;
    WebView   webView;

    StorageReference storageRef;
    String test;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Button back,next;

    public static final String LESSON_NAME="lessonName";
    public static final String TEXT="contextText";
    public static final String  url ="test";
    public static final String  TYPE ="type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        //gs reference e URI luat in FileUpload, deci ii dau string url in loc de link
        titleview=findViewById(R.id.LessonTitle);
        textview=findViewById(R.id.TextLesson);
        //  testView=findViewById(R.id.Test);
        back=findViewById(R.id.backPage);
        next=findViewById(R.id.followingPage);


        Intent intent=getIntent();
        test=intent.getStringExtra(ListArticles.TEST);

        storageRef=storage.getReferenceFromUrl(test);

        webView=findViewById(R.id.webView);

        //  webView.loadUrl();

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                //aici se incarca webView-ul
                Uri downloadUrl = uri;
                test = downloadUrl.toString();
                // testView.setText(test);
                webView.loadUrl(test);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


        String title=intent.getStringExtra(ListArticles.LESSON_NAME);
        String text=intent.getStringExtra(ListArticles.TEXT);



        //  testView.setText(test);
        titleview.setText(title);
        textview.setText(text);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.super_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.backmenu:
                startActivity(new Intent(LessonActivity.this, ListArticles.class));
                break;
            case R.id.nextmenu:
                Intent intent=getIntent();
                String title=intent.getStringExtra(ListArticles.LESSON_NAME);
                String test=intent.getStringExtra(ListArticles.TEST);
                String text=intent.getStringExtra(ListArticles.TEXT);
                String nexturl=intent.getStringExtra(ListArticles.TYPE);

                Intent intent2=new Intent(getApplicationContext(), LessonActivity.class);
                intent2.putExtra(LESSON_NAME, title);
                intent2.putExtra(TEXT, text);
                intent2.putExtra(url, test);
                intent2.putExtra(TYPE, nexturl);

                startActivity(intent2);
                break;

        }


        return super.onOptionsItemSelected(item);
    }


}