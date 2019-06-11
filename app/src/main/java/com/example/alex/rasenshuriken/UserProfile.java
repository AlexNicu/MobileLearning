package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserProfile extends AppCompatActivity {

    private static final int TAKE_IMAGE = 102;
    ImageView imgView;
    EditText Username;
String displayName;
    Uri uriImage;
    ProgressBar pb;
    String imageUrl;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth=FirebaseAuth.getInstance();

        imgView= findViewById(R.id.imgView);
        Username= findViewById(R.id.etUserName);
        pb=findViewById(R.id.progressbar);




        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        loadingUserInfo();

        findViewById(R.id.btSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveUserInfo();

                Intent i = new Intent(UserProfile.this,TopArticles.class);
                startActivity(i);
            }
        });

    }


    private void loadingUserInfo() {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgView);
            }
            if (user.getDisplayName() != null) {
                Username.setText(user.getDisplayName());
            }
        }
    }

    private void saveUserInfo() {
        String displayText=Username.getText().toString();
        SharedPreferences sharedpref2=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2=sharedpref2.edit();
        editor2.putString("username",Username.getText().toString());
        editor2.apply();
        if(displayText.isEmpty()){
            Username.setError("Text Required");
            Username.requestFocus();
            return;
        }
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null && imageUrl != null){
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder().setDisplayName(displayText).setPhotoUri(Uri.parse(imageUrl)).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserProfile.this,"Page Updated",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserProfile.this,FileUpload.class);
                        intent.putExtra("username",displayText);
                    }
                }
            });
        }
        return ;
        //verificare user login (salvare credentiale, sa nu mai apara dupa ce este setat, adaugat profil in menu bar
        //menu bar
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_IMAGE && resultCode == RESULT_OK && data != null && data.getData()!=null){
            uriImage=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
                imgView.setImageBitmap(bitmap);
                uploadImageFirebase();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void uploadImageFirebase() {
        StorageReference ImageRef = FirebaseStorage.getInstance().getReference("images/"+System.currentTimeMillis()+".jpg");
        if(uriImage!=null){
            pb.setVisibility(View.VISIBLE);
            ImageRef.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pb.setVisibility(View.GONE);
                    imageUrl=taskSnapshot.getStorage().getDownloadUrl().toString();
                }

            })
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pb.setVisibility(View.GONE);
                    Toast.makeText(UserProfile.this, e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }


    private void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), TAKE_IMAGE);



    }




}
