package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListArticles extends AppCompatActivity {

    ListView listViewLessons;
    DatabaseReference databaseLesson;
    DatabaseReference databaseText, fileUpload;
    List<Lesson> lessonsList;
    List<Upload> uploadList;
    List<TextMessage> messageList;
    public int counter2=0;
    public int counter=0;
    int page;
    TextMessage textMessage;

    public static final String LESSON_NAME="lessonName";
    public static final String TEXT="contextText";
    public static final String TEST="test";
   // public static final String TYPE="type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        messageList = new ArrayList<>();
        lessonsList=new ArrayList<>();
        uploadList=new ArrayList<>();

        listViewLessons=findViewById(R.id.ArticlesList);

        databaseLesson= FirebaseDatabase.getInstance().getReference("Lessons");
        databaseText=FirebaseDatabase.getInstance().getReference("Uploads");
        fileUpload=FirebaseDatabase.getInstance().getReference("FileUploads");

        fileUpload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot textSnapshot : dataSnapshot.getChildren()){
                    Upload upload=textSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                    counter2++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseText.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot textSnapshot : dataSnapshot.getChildren()) {

                    TextMessage textMessage = textSnapshot.getValue(TextMessage.class);
                    messageList.add(textMessage);
                    counter++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


      //  SharedPreferences sharedpref8 = getSharedPreferences("typeInfo", Context.MODE_PRIVATE);
     //   String type = sharedpref8.getString("type", "nu-merge");

        Intent intent=new Intent(getApplicationContext(), LessonActivity.class);
        listViewLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lesson lesson = lessonsList.get(position);
                intent.putExtra(TEXT,  "No text available");//implicit, dacă nu va găsi un mesaj asociat

                for (TextMessage tm : messageList) {
                    if (lesson.getLessonId().equals(tm.getLessonID())) {//poate compari dupa id-ul lectiei si nu dupa titlu
                        intent.putExtra(TEXT, tm.getTextmessage());//a gasit, inlocuieste textul implicit de mai sus cu valoarea din Firebase
                        break;//nu mai mergem mai departe în for, am gasit ceea ce cautam
                    }
                }
                for(Upload up : uploadList){
                    if (lesson.getLessonId().equals(up.getLessonId())){
                        intent.putExtra(TEST,up.getLinkUrl());
                        break;
                    }
                }

                intent.putExtra(LESSON_NAME, lesson.getTitle());
             //   intent.putExtra(TYPE,type);
                startActivity(intent);
            }
        });

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
