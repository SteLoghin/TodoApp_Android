package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {

    private final Context context;
    
    private final ArrayList<Todo> values;


    public TodoAdapter(@NonNull Context context, ArrayList<Todo> values) {
        super(context, R.layout.row_todo, values);
        this.context=context;
        this.values=values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.row_todo,parent,false);

        TextView todoTitle=rowView.findViewById(R.id.todoTitle);
        TextView todoCreatedAt=rowView.findViewById(R.id.todoCreatedAt);

        todoTitle.setText(values.get(position).getTitle());
        todoCreatedAt.setText(values.get(position).getDate());

        return rowView;
    }
}
