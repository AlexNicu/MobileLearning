package com.example.alex.rasenshuriken;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LessonList extends ArrayAdapter<Lesson> {

    private Activity context;
    private List<Lesson> lessonList;

    public LessonList(Activity context, List<Lesson> lessonList){
        super(context,R.layout.activity_list_articles,lessonList);
        this.context=context;
        this.lessonList=lessonList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewItem=inflater.inflate(R.layout.activity_list_articles,null,true);

        TextView textViewTitle=listviewItem.findViewById(R.id.textViewTitle);
        TextView textViewUsername=listviewItem.findViewById(R.id.textViewUsername);

        Lesson lesson=lessonList.get(position);
        textViewTitle.setText(lesson.getTitle());
        textViewUsername.setText(lesson.getUsername());

        return listviewItem;
    }
}
