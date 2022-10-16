package com.example.finalproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends  RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private Context context;
    private List<Todo> todoList;

    public ListAdapter(Context context, List<Todo> todoList, DeleteItemListener deleteItemListener) {
        this.context = context;
        this.todoList = todoList;
        this.deleteItemListener = deleteItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.taskName.setText(todo.getTask());
        //holder.id.setText(String.valueOf(todo.getId()));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView taskName;
        private TextView id;
        private Button deleteButton;

        public ViewHolder(@NonNull View view) {
            super(view);

            id = view.findViewById(R.id.id);
            taskName = view.findViewById(R.id.task);
            deleteButton = view.findViewById(R.id.done);

            deleteButton.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (v.getId() == R.id.done) {
                deleteItemListener.onDeleteItemClick(position);
            }

        }
    }

    private DeleteItemListener deleteItemListener;

    public interface DeleteItemListener {
        void onDeleteItemClick(int position);
    }


}





