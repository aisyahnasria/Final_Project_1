package com.example.finalproject1;

public class Todo {
    int id;
    String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Todo() {
        super();
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Todo (String task){
        this.task = task;
    }

}
