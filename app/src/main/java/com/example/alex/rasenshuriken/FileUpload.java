package com.example.alex.rasenshuriken;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FileUpload extends AppCompatActivity {

    Button selectFile, upload, nextPage;
    TextView notification;
    Uri pdfUri; //actually urls that are meant for local storage
    FirebaseStorage storage; //for uploading files
    FirebaseDatabase database; //storing URLs of uploaded files
    ProgressDialog progressDialog;
    String pivot,name,subject,subdomain,title,page;
     int keeper = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        storage=FirebaseStorage.getInstance(); //returns an object of FireBase Storage
        database=FirebaseDatabase.getInstance();

        selectFile=findViewById(R.id.selectFile);
        upload=findViewById(R.id.uploadFile);
        notification=findViewById(R.id.textViewfa);
        pivot=getIntent().getExtras().getString("value");
        SharedPreferences sharedpref2=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //putstring name la logare, iar la delogare sa fie null
        // get int la keeper si sa il modific la int
        //upload la prima pagina putint cu 1, dupa ce apesi next, se ia get int keeper, dar si modificare putint keeper=2;
        name=sharedpref2.getString("username", "nu-merge");
        SharedPreferences sharedpref3=getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
        subdomain=sharedpref3.getString("subfilename", "nu-merge");
        subject=sharedpref3.getString("filename", "Nu-merge");
        SharedPreferences sharedpref4=getSharedPreferences("uploadInfo",Context.MODE_PRIVATE);
        page=sharedpref4.getString("page","nu-merge");
        title= sharedpref4.getString("title","nu-merge");


        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(FileUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(FileUpload.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},7);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                uploadFile(pdfUri);
                else
                    Toast.makeText(FileUpload.this, "A file must be selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void uploadFile(Uri pdfUri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();

        String fileName=System.currentTimeMillis()+"";
        StorageReference storageReference=storage.getReference(); //returns the root path
        storageReference.child("Uploads/"+subject+"/"+subdomain+"/"+name+"/"+page).child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url= taskSnapshot.getStorage().getDownloadUrl().toString(); //return the url of the uploaded file
                //storing the url in the database
                DatabaseReference reference=database.getReference(); //return the path to root
                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(FileUpload.this," The file was successfully uploaded",Toast.LENGTH_SHORT).show();
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
                int currentProgress= (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       //checks if the user has granted permission
        if(requestCode==7 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else
            Toast.makeText(FileUpload.this, "You need to provide permission in order to upload content", Toast.LENGTH_LONG).show();
    }

    private void selectPdf() {
        Intent intent = new Intent();
        if (pivot.contains("file")) {
            intent.setType("application/pdf");
        }
        else if(pivot.contains("audio")){
            intent.setType("audio/*");
        }
        else if (pivot.contains("video")){
            intent.setType("video/*");
        }
        else if (pivot.contains("image")){
            intent.setType("images/*");
        }
        else{
            Toast.makeText(FileUpload.this,"Error last else",Toast.LENGTH_LONG).show();
        }
        intent.setAction(Intent.ACTION_GET_CONTENT); //fetch files
        startActivityForResult(intent,8);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //check if the user has selected a file or not
        if(requestCode==8 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData(); // returns the uri of the selected file
            notification.setText("A file has been selected: "+data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(FileUpload.this, " A file must be selected", Toast.LENGTH_LONG).show();
        }

    }
//TODO Keeper este variabila de auto-incrementare, trecuta prin sharedPreference4 la adresa de salvare a documentului salvat
    //Principiul este ca atunci cand utilizatorul salveaza un fisier, eu ii trimit prin denumirea fisierului
    //si adresa unde sa se salveze, iar keeper ar trebui sa fie o variabila care se schimba constant
    //pentru a crea un nou fisier de fiecare data cand buttonul respectiv se apasa "next page".
    public void NextPage(View view) {
        SharedPreferences sharedpref4=getSharedPreferences("uploadInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4=sharedpref4.edit();
        nextPage=findViewById(R.id.nextPage);
        nextPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String ok= ""+keeper;
                editor4.putString("page",ok);
               // editor4.putInt("page",keeper);
                editor4.apply();
                keeper=keeper+1;
                startActivity(new Intent(FileUpload.this, UploadActivity.class));
            }
        });
        // get int la keeper si sa il modific la int
        //upload la prima pagina putint cu 1, dupa ce apesi next, se ia get int keeper, dar si modificare putint keeper=2;
    }
}
