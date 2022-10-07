package com.example.finalproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    String []data={"bangun Pagi","cuci muka","sarapan"};
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> items = new LinkedList<>();
        items.add("olahraga");

        RecyclerView recyclerView = findViewById(R.id.listItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter(items);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.button).setOnClickListener(view ->{
            popup(null);
            items.add(data[count%3]);
            count++;
            adapter.notifyItemInserted(items.size()-1);
        });
    }


    void popup(String Todo){
        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        EditText input =(EditText) view.findViewById(R.id.editToDo);
        Button saveButton = (Button) view.findViewById(R.id.buttonAdd);

        popupBuilder.setView(view);
        AlertDialog popupForm = popupBuilder.create();
        popupForm.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Todo = input.getText().toString();
                popupForm.dismiss();
            }
        });
    }
}