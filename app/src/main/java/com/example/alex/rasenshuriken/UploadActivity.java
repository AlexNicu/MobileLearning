package com.example.alex.rasenshuriken;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {
private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
   private ImageView imgViewUp;
    private EditText imageName;
    private Uri imgUri;
    ImageButton image,image2,video,video2,audio,audio2,file,file2;
    TextView tl,tl2,tl3,tl4;
    Button nextPage;
private  int keeper;
public static final String Storage_Path="image/";
public static final String Database_Path="image";
    public static final int Request_Code=1234;
   String OK=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        nextPage=findViewById(R.id.nextPage);
        mStorageRef= FirebaseStorage.getInstance().getReference();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference(Database_Path);

        image=findViewById(R.id.imageButton3);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="image";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        image2=findViewById(R.id.imageButton2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="image";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        audio=findViewById(R.id.imageButton7);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="audio";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        audio2=findViewById(R.id.imageButton8);
        audio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="audio";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        video=findViewById(R.id.imageButton4);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="video";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        video2=findViewById(R.id.imageButton6);
        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="video";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        file=findViewById(R.id.imageButton9);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="file";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        file2=findViewById(R.id.imageButton12);
        file2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="file";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        tl=findViewById(R.id.textlink);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="file";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        tl2=findViewById(R.id.textlink2);
        tl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="audio";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        tl3=findViewById(R.id.textlink3);
        tl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="video";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });
        tl4=findViewById(R.id.textlink4);
        tl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK="image";
                Intent i = new Intent(UploadActivity.this,FileUpload.class);
                i.putExtra("value",OK);
                startActivity(i);
            }
        });


    }
//button 2 and 3 + textlink4
    public void btnBrowseImg(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select image"),Request_Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request_Code && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            imgUri=data.getData();
            try{
                Bitmap bm= MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                imgViewUp.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImage(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void btnUpload(View view) {
        if(imgUri!=null){
            final ProgressDialog dialog= new ProgressDialog(this);
            dialog.setTitle("Uploading image");
            dialog.show();
            //get the storage reference
            StorageReference ref=mStorageRef.child(Storage_Path + System.currentTimeMillis() + "." + getImage(imgUri));


            //add file to reference

            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //dismiss dialog when success
                    dialog.dismiss();
                    //display success toast msg
                    Toast.makeText(getApplicationContext(),"Image uploaded", Toast.LENGTH_SHORT).show();
                    ImageUpload imageUpload=new ImageUpload(imageName.getText().toString(),taskSnapshot.getStorage().getDownloadUrl().toString());

                    //Save image info in the firebase database
                    String uploadId=mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(imageUpload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //dismiss dialog when error
                    dialog.dismiss();
                    //display success toast msg
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //show upload progress
              //  double progress=(100* taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                dialog.setMessage("Uploaded");
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Please select an image", Toast.LENGTH_SHORT).show();

        }

    }

    public void btnShow(View v){
        Intent i = new Intent(UploadActivity.this,ImageListActivity.class);
        startActivity(i);
    }
    public void btnVideo(View v){
        Intent i = new Intent(UploadActivity.this,VideoUpload.class);
        startActivity(i);
    }
    public void btnFile(View v){
        Intent i = new Intent(UploadActivity.this,FileUpload.class);
        startActivity(i);
    }


}
