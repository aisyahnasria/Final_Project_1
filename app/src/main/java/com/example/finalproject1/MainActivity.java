package com.example.finalproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener,
    ListAdapter.DeleteItemListener {

    RecyclerView recyclerView;
    Button addButton;

    DatabaseHelper db;
    List<Todo> todoList;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.listItem);
        addButton = (Button) findViewById(R.id.button);
        addButton.setOnClickListener(this);

        loadData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            popup(null  , true);
        }
    }


    void popup(String Todo, boolean isAddNew){
        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);


        EditText input =(EditText) view.findViewById(R.id.editToDo);
        Button saveButton = (Button) view.findViewById(R.id.buttonAdd);

        if (!isAddNew) {
            input.setText(Todo);
        }

        popupBuilder.setView(view);
        AlertDialog popupForm = popupBuilder.create();
        popupForm.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Task = input.getText().toString();
                Todo todo = new Todo(Task);

                if (isAddNew) {
                    db.addTodo(todo);
                    loadData();
                    Toast.makeText(MainActivity.this,"Berhasil " ,Toast.LENGTH_SHORT).show();
                    popupForm.dismiss();
                }


            }
        });
    }

    public void loadData() {
        todoList = db.getAllTodo();
        listAdapter = new ListAdapter(this, todoList, this );
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDeleteItemClick(int position) {
        Todo todo = todoList.get(position);
        Toast.makeText(MainActivity.this,"congratulation you have done the task",Toast.LENGTH_SHORT).show();
        db.CompleteTodo(todo);
        loadData();

    }
}