package com.example.finalproject1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends  RecyclerView.Adapter<Demo>{
    List<String> items;

    public ListAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Demo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new  Demo(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull Demo holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class Demo extends RecyclerView.ViewHolder {
    TextView textView;
    private ListAdapter adapter;
    public Demo(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        itemView.findViewById(R.id.done).setOnClickListener(view ->{
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }
    public Demo linkAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
