package com.example.alex.rasenshuriken;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imgList=new ArrayList<>();
        lv=findViewById(R.id.listViewImage);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait while loading...");
        progressDialog.show();

        mDatabaseRef= FirebaseDatabase.getInstance().getReference(UploadActivity.Database_Path);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                //Fetch image data from firebase database
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    //image upload class require default constructor
                  //  ImageUpload img=snapshot.getValue(ImageUpload.class);
                //    imgList.add(img);
                }
                //Initialize adapter
                adapter=new ImageListAdapter(ImageListActivity.this,R.layout.image_item,imgList);
                //Set adapter for listview
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });

    }
}
