package com.example.finalproject1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static  final String DB_NAME = "TODOLIST";
    private static final  String TABLE_DATA = "task";
    private static final  String KEY_ID = "id";
    private static final String TASK = "taskname";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableData = "CREATE TABLE "+TABLE_DATA+ " ("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TASK + " TEXT )";

        db.execSQL(tableData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DATA);
        onCreate(db);
    }

    public List<Todo>getAllTodo(){
        List<Todo> todoList = new ArrayList<>();

        String allTodo = "SELECT * FROM "+TABLE_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(allTodo, null);

        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo();
                todo.setId(Integer.parseInt(cursor.getString(0)));
                todo.setTask(cursor.getString(1));
                todoList.add(todo);
            } while (cursor.moveToNext());
        }
        return todoList;
    }

     void addTodo(Todo todo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK, todo.getTask());
        Log.i("DBHELPER ", todo.getTask());
        db.insert(TABLE_DATA,null, values);
        db.close();
    }



    public void  CompleteTodo(Todo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_ID + " = ?",
                new String[] {String.valueOf(todo.getId())});
        db.close();

    }
}
