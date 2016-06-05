package com.gdgand.rxjava.rxjavasample.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdgand.rxjava.rxjavasample.R;
import com.gdgand.rxjava.rxjavasample.views.recyclerview.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<String> textList;
    private Context context;
    private RecyclerOnItemClickListener onItemClickListener;

    public MainAdapter(Context context) {
        this.context = context;
        textList = new ArrayList<>();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.tvTitle.setText(getItem(position));

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(MainAdapter.this, position);
            }
        });
    }

    public String getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            return "";
        }
        return textList.get(position);
    }

    public void add(String title) {
        textList.add(title);
    }

    public void addAll(List<String> titles) {
        textList.addAll(titles);
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    public void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_main_item)
        TextView tvTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
