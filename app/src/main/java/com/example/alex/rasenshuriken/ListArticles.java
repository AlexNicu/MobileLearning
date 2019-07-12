package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListArticles extends AppCompatActivity {

    ListView listViewLessons;
    DatabaseReference databaseLesson;
    List<Lesson> lessonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        lessonsList=new ArrayList<>();
        listViewLessons=findViewById(R.id.ArticlesList);
        databaseLesson= FirebaseDatabase.getInstance().getReference("Lessons");



    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseLesson.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                lessonsList.clear();
                for(DataSnapshot lessonSnapshot: dataSnapshot.getChildren()){
                    Lesson lesson=lessonSnapshot.getValue(Lesson.class);
                    lessonsList.add(lesson);
                }
                LessonList adapter=new LessonList(ListArticles.this,lessonsList);
                listViewLessons.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
