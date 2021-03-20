package com.example.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private final String COL0 = "ID";
    private final String COL1 = "title";
    private final String COL2 = "description";
    private final String COL3 = "createdAt";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    //    se apeleaza prima oara cand baza de date e accesata
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement="CREATE TABLE TODO_TABLE ()";

        db.execSQL(createTableStatement);
    }

    //    cand se schimba baza de date, cand va fi alta versiune, cand se schimba structura bazei de date
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
