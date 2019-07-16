package com.example.alex.rasenshuriken;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;

public class FileUpload extends AppCompatActivity  {

    Button selectFile, upload, nextPage, finish;
    TextView notification;
    EditText PText;
    Uri pdfUri; //actually urls that are meant for local storage
    FirebaseStorage storage; //for uploading files
    FirebaseDatabase database; //storing URLs of uploaded files
    ProgressDialog progressDialog;
    String pivot, name, subject, subdomain, title, saver;
    DatabaseReference mDatabaseReference;
    DatabaseReference databaseLesson;
    private Uri ImageUri;
    int page;
    StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        mStorageRef=FirebaseStorage.getInstance().getReference("Uploads");

        storage = FirebaseStorage.getInstance(); //returns an object of FireBase Storage
        database = FirebaseDatabase.getInstance();
        PText=(EditText)findViewById(R.id.PText);
        selectFile = findViewById(R.id.selectFile);
        upload = findViewById(R.id.uploadFile);
        notification = findViewById(R.id.textViewfa);
        pivot = getIntent().getExtras().getString("value");
        //putstring name la logare, iar la delogare sa fie null
        // get int la keeper si sa il modific la int
        //upload la prima pagina putint cu 1, dupa ce apesi next, se ia get int keeper, dar si modificare putint keeper=2;
        SharedPreferences sharedpref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        name = sharedpref2.getString("username", "nu-merge");
        SharedPreferences sharedpref3 = getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
        subdomain = sharedpref3.getString("subfilename", "nu-merge");
        subject = sharedpref3.getString("filename", "Nu-merge");
        SharedPreferences sharedpref4 = getSharedPreferences("uploadInfo", Context.MODE_PRIVATE);

        //daca pagina nu exista, valoarea initiala este 0
        page = sharedpref4.getInt("page", 0);
        title = sharedpref3.getString("title", "nu-merge");

        mDatabaseReference=FirebaseDatabase.getInstance().getReference("Uploads");
        SharedPreferences sharedpref6 = getSharedPreferences("lessonID", Context.MODE_PRIVATE);
        String lessonId = sharedpref6.getString("ID", "nu-merge");

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(FileUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else {
                    ActivityCompat.requestPermissions(FileUpload.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 7);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(pdfUri != null && PText!=null){
                    uploadFile(pdfUri);
                    saver= PText.getText().toString();
                    String id=  mDatabaseReference.push().getKey();
                    TextMessage tm=new TextMessage(id,lessonId,saver,subject,subdomain,title,name,page);
                    mDatabaseReference.child(id).setValue(tm);
                    Toast.makeText(FileUpload.this, "The text was uploaded", Toast.LENGTH_SHORT).show();
                }
                else if (PText!=null && pdfUri ==null){
                    saver= PText.getText().toString();
                    String id=  mDatabaseReference.push().getKey();
                    TextMessage tm=new TextMessage(id,lessonId,saver,subject,subdomain,title,name,page);
                    mDatabaseReference.child(id).setValue(tm);
                    Toast.makeText(FileUpload.this, "The text was uploaded", Toast.LENGTH_SHORT).show();
                }
                else if (pdfUri != null )
                      uploadFile(pdfUri);
                else
                    Toast.makeText(FileUpload.this, "A file or a text must be inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check if the user has selected a file or not
        if (requestCode == 8 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData(); // returns the uri of the selected file
            notification.setText("A file has been selected: " + data.getData().getLastPathSegment());


        } else {
            Toast.makeText(FileUpload.this, " A file must be selected", Toast.LENGTH_LONG).show();
        }

    }

    private void uploadFile(Uri pdfUri) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();

        String fileName = System.currentTimeMillis() + "."+getFileExtension(pdfUri);
        StorageReference storageReference = storage.getReference(); //returns the root path
        storageReference.child("Uploads/" + subject + "/" + subdomain + "/" + name + "/" + page).child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               String url = taskSnapshot.getStorage().getDownloadUrl().toString(); //return the url of the uploaded file
                DatabaseReference reference = database.getReference(); //return the path to root
               Upload upload = new Upload(fileName,url);
               String uploadId=mDatabaseReference.push().getKey();
               mDatabaseReference.child(uploadId).setValue(upload);

                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(FileUpload.this, " The file was successfully uploaded", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(FileUpload.this, "The file was not uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FileUpload.this, "The file was not uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //checks if the user has granted permission
        if (requestCode == 7 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else
            Toast.makeText(FileUpload.this, "You need to provide permission in order to upload content", Toast.LENGTH_LONG).show();
    }

    private void selectPdf() {
        Intent intent = new Intent();
        if (pivot.contains("file")) {
            intent.setType("application/pdf");
        } else if (pivot.contains("audio")) {
            intent.setType("audio/*");
        } else if (pivot.contains("video")) {
            intent.setType("video/*");
        } else if (pivot.contains("image")) {
            intent.setType("image/*");
        } else {
            Toast.makeText(FileUpload.this, "Error last else", Toast.LENGTH_LONG).show();
        }
        intent.setAction(Intent.ACTION_GET_CONTENT); //fetch files
        startActivityForResult(intent, 8);
    }



    public void NextPage(View view) {
        SharedPreferences sharedpref4 = getSharedPreferences("uploadInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedpref4.edit();
        nextPage = findViewById(R.id.nextPage);
        nextPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //salvam pagina urmatoare, incrementind pagina curenta
                editor4.putInt("page", ++page);
                editor4.apply();
                startActivity(new Intent(FileUpload.this, UploadActivity.class));
            }
        });
        // get int la keeper si sa il modific la int
        //upload la prima pagina putint cu 1, dupa ce apesi next, se ia get int keeper, dar si modificare putint keeper=2;
    }

    public void Finish(View view){
        finish=findViewById(R.id.Finish);
        SharedPreferences sharedpref2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedpref2.getString("username", "nu-merge");
        SharedPreferences sharedpref3 = getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
        String title = sharedpref3.getString("title", "tilulNu-merge");
        String subdomain = sharedpref3.getString("subfilename", "subdomainNu-merge");
        String subject = sharedpref3.getString("filename", "subjectNu-merge");
        SharedPreferences sharedpref6 = getSharedPreferences("lessonID", Context.MODE_PRIVATE);
        String lessonId = sharedpref6.getString("ID", "nu-merge");

        finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FileUpload.this, "The lesson has been published", Toast.LENGTH_LONG).show();

                databaseLesson= FirebaseDatabase.getInstance().getReference("Lessons");
                String id=databaseLesson.push().getKey();
                Lesson lesson=new Lesson(lessonId, subject,subdomain,title,name);
                databaseLesson.child(lessonId).setValue(lesson);

                page=0;
                SharedPreferences sharedpref4 = getSharedPreferences("uploadInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharedpref4.edit();
                editor4.putInt("page", page);
                editor4.apply();
                startActivity(new Intent(FileUpload.this, TopArticles.class));

            }
        });




    }



}
