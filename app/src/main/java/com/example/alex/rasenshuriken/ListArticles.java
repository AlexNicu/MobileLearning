package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListArticles extends AppCompatActivity {

    ListView listView;
    ArrayList<String> myArrayList= new ArrayList<>();
    Firebase myFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
//TODO Daca folosesc link-ul pentru titluri, da eroare (pe internet am gasit ceva legat de faptul ca
// string-ul nu poate fi serializat ca JSON si probabil ar trebui sa creez o clasa de obiecte si sa returnez obiect in loc de string
        Firebase.setAndroidContext(this);
        myFirebase= new Firebase("https://rasenshuriken-4e3c7.firebaseio.com/Titles");

        listView=findViewById(R.id.ArticleslistView);
        ArrayAdapter<String> myArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myArrayList);
        listView.setAdapter(myArrayAdapter);
        myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String myChildValues=dataSnapshot.getValue(String.class);
                myArrayList.add(myChildValues);
                myArrayAdapter.notifyDataSetChanged();
                startActivity(new Intent(ListArticles.this, LessonActivity.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


}
