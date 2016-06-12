package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.hotandcold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;

import java.util.ArrayList;
import java.util.List;

public class HotAndColdAdapter extends RecyclerView.Adapter<HotAndColdAdapter.ViewHolder> {

    private Context context;
    private List<String> items = new ArrayList<>();

    public HotAndColdAdapter(Context context) {
        this.context = context;
    }

    public void clearItems() {
        this.items = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addItem(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_and_cold, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewItem.setText(getItem(position));
    }

    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewItem)
        TextView textViewItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
