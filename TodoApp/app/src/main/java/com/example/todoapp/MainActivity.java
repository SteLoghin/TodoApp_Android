package com.example.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_TODO_REQUEST_CODE = 10;
    ListView listView;
    ArrayList<Todo> todos;
    EditText etGetTitle;
    EditText etGetDescription;
    Button btnCreateTodo;
    TodoAdapter todoAdapter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        etGetTitle = findViewById(R.id.etCreateTitle);
        etGetDescription = findViewById(R.id.etCreateDescription);
        btnCreateTodo = findViewById(R.id.btnCreateTodo);

        btnCreateTodo.setOnClickListener(v -> {
            createTodo();
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);

        todos = databaseHelper.selectAll();
        Log.d("LIST_SIZE", String.valueOf(todos.size()));

        todoAdapter = new TodoAdapter(this, todos);

        listView.setAdapter(todoAdapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTodo = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(MainActivity.this, TodoActivity.class);
            intent.putExtra("Todo", todos.get(position));
            startActivityForResult(intent, EDIT_TODO_REQUEST_CODE);
        });
    }

    private void createTodo() {
        String title = etGetTitle.getText().toString().trim();
        String description = etGetDescription.getText().toString().trim();
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Introdu date", Toast.LENGTH_SHORT).show();
        } else {
            String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
            Todo newTodo = new Todo(title, description, date);
            todos.add(newTodo);
            boolean inserted = databaseHelper.insertTodo(newTodo);
            Toast.makeText(this, "Success=" + inserted, Toast.LENGTH_SHORT).show();
            todoAdapter.notifyDataSetChanged();
            listView.setAdapter(todoAdapter);
            etGetTitle.setText("");
            etGetDescription.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_TODO_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.getStringExtra("operation").equals("edit")) {
                Todo changedTodo = data.getParcelableExtra("Todo");
                for (int i = 0; i < todos.size(); i++) {
                    if (todos.get(i).getID() == changedTodo.getID()) {
                        todos.set(i, changedTodo);
                        todoAdapter.notifyDataSetChanged();
                        listView.setAdapter(todoAdapter);
                    }
                }
//                Toast.makeText(this, data.getParcelableExtra("Todo").toString(), Toast.LENGTH_SHORT).show();
            } else if (data.getStringExtra("operation").equals("delete")) {
                int idToBeDeleted = data.getIntExtra("todoID", 0);
                for (int i = 0; i < todos.size(); i++) {
                    if (todos.get(i).getID() == idToBeDeleted) {
                        todos.remove(todos.get(i));
                        todoAdapter.notifyDataSetChanged();
                        listView.setAdapter(todoAdapter);
                    }
                }
            }
        }


    }
}