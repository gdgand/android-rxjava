package com.gdgand.rxjava.rxjavasample.queue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Queue2Adapter extends RecyclerView.Adapter<Queue2Adapter.QueueViewHolder> {

    private Context context;
    private List<String> titles;

    public Queue2Adapter(Context context) {
        this.context = context;
        titles = new ArrayList<>();
    }

    public void add(String title) {
        titles.add(title);
    }

    @Override
    public Queue2Adapter.QueueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView view = new TextView(context);
        view.setTextSize(20);
        view.setPadding(20, 20, 20, 20);
        return new QueueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Queue2Adapter.QueueViewHolder holder, int position) {
        holder.tvTitle.setText(getItem(position));
    }

    private String getItem(int position) {
        return titles.get(position);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void add(int index, String title) {
        titles.add(index, title);
    }

    static class QueueViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public QueueViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView;
        }
    }
}
