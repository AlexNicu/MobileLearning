package com.example.alex.rasenshuriken;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Titles.db";
    private static final String DB_TABLE="Lessons_Table";

    //columns
    private static final String ID= "ID";
    private static final String TITLE= "TITLE";
    private static final String USERNAME= "USERNAME";
private static final int SCORE=0;
    private static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +TITLE+" TITLE , "+ USERNAME+" USERNAME " + ")";


    public DatabaseHelper(Context context){
        super(context, DB_NAME, null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    //create method to insert data
    public boolean insertData(String username, String Title){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLE,Title);
        contentValues.put(USERNAME,username );

        long result=db.insert(DB_TABLE, null, contentValues);
        return result !=-1; //if result = -1 data doesn't gets inserted
    }

    //create method to view Data
    public Cursor viewData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from "+ DB_TABLE;
        Cursor cursor =db.rawQuery(query,null);

        return cursor;
    }

}
