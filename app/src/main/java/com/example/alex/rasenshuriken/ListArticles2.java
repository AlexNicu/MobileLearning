package com.example.alex.rasenshuriken;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListArticles2 extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        db=new DatabaseHelper(this);
        listItem=new ArrayList<>();

        lv=findViewById(R.id.ArticleslistView);
        viewData();
        listItem.clear();
        viewData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text=lv.getItemAtPosition(position).toString();
                Toast.makeText(ListArticles2.this,""+text,Toast.LENGTH_LONG).show();
            }
        });

    }

    public  void viewData() {
        Cursor cursor= db.viewData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1)); //index 1 is name, index 0 is id
                listItem.add(cursor.getString(2));
            }
            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            lv.setAdapter(adapter);
        }

    }


}
