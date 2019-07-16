package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

public class LessonActivity extends AppCompatActivity {

    TextView titleview;
    TextView textview, testView;
    WebView   webView;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);


        //gs reference e URI luat in FileUpload, deci ii dau string url in loc de link
        titleview=findViewById(R.id.LessonTitle);
        textview=findViewById(R.id.TextLesson);
         testView=findViewById(R.id.Test);

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
                testView.setText(test);
                //webView.loadUrl(test);
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




}
