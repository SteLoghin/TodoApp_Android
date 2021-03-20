package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_CREATED_AT = "CREATED_AT";
    public static final String COLUMN_ID = "ID";
    public static final String TODO_TABLE = "TODO_TABLE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    //    se apeleaza prima oara cand baza de date e accesata
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + TODO_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT," +
                " " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_CREATED_AT + " TEXT )";

        db.execSQL(createTableStatement);
    }

    //    cand se schimba baza de date, cand va fi alta versiune, cand se schimba structura bazei de date
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertTodo(Todo todo){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_TITLE,todo.getTitle());
        cv.put(COLUMN_DESCRIPTION,todo.getDescription());
        cv.put(COLUMN_CREATED_AT,todo.getDate());

        long insert = db.insert(TODO_TABLE, null, cv);

        db.close();

        return insert != -1;
    }

    public ArrayList<Todo> selectAll(){
        ArrayList<Todo> results=new ArrayList<>();
        String sql="SELECT * from " + TODO_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String title=cursor.getString(1);
                String description=cursor.getString(2);
                String date=cursor.getString(3);
                results.add(new Todo(id,title,description,date));
            }while(cursor.moveToNext());
        }else

        db.close();
        cursor.close();
        return results;
    }
}
