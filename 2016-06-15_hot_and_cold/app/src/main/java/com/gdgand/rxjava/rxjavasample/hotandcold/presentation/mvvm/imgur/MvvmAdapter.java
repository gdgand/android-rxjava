package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvvm.imgur;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;

import java.util.Collections;
import java.util.List;

public class MvvmAdapter extends RecyclerView.Adapter<MvvmAdapter.ViewHolder> {

    private Context context;
    private List<String> posts = Collections.emptyList();

    public MvvmAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mvvm_imgur, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewItem.setText(getItem(position));
    }

    public String getItem(int position) {
        return posts.get(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
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
