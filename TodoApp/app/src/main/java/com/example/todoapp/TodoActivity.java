package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TodoActivity extends AppCompatActivity {

    EditText editTitle, editDescription;
    TextView createdAt;
    Button btnSaveChanges, btnDelete;
    Todo todo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Intent intent = getIntent();
        todo = intent.getParcelableExtra("Todo");

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        createdAt = findViewById(R.id.createdAt);

        editTitle.setText(todo.getTitle());
        editDescription.setText(todo.getDescription());
        String date = createdAt.getText().toString() + todo.getDate();
        createdAt.setText(date);
        Toast.makeText(this, "ID este: "+String.valueOf(todo.getID()), Toast.LENGTH_LONG).show();

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnDelete = findViewById(R.id.btnDelete);

        btnSaveChanges.setOnClickListener(v -> saveChanges());
        btnDelete.setOnClickListener(v -> deleteTodo());
    }

    private void deleteTodo() {
        Intent intent=new Intent();
        intent.putExtra("operation","delete");
        intent.putExtra("todoID",todo.getID());
        setResult(RESULT_OK,intent);
        TodoActivity.this.finish();
    }

    private void saveChanges() {
        todo.setTitle(editTitle.getText().toString().trim());
        todo.setDescription(editDescription.getText().toString().trim());
        Intent intent=new Intent();
        intent.putExtra("operation","edit");
        intent.putExtra("Todo",todo);
        setResult(RESULT_OK,intent);
        TodoActivity.this.finish();
    }
}