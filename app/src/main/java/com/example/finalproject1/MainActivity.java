package com.example.finalproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ArrayList<String> items;
    private ArrayAdapter<String>itemsAdapter;

    Button button;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.listItem);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
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




    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            System.out.println("berhasil");
            popup(null);
        }
    }
}