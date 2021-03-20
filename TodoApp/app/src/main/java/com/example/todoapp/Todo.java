package com.example.todoapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Todo implements Parcelable {

    private int ID;
    private String title;
    private String description;
    private String date;

    protected Todo(Parcel in) {
        ID = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Todo(String title, String description, String date) {
//        this.title = title;
//        this.description = description;
//        this.date = date;
//    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Todo(int ID, String title, String description, String date) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Todo(){

    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: "+ ID +"\nTitle: " + title + "\nDescription: " + description + "\nCreated at: " + date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
    }
}
