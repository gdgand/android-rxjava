package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.Topic;

import java.util.Collections;
import java.util.List;

public class ImgurAdapter extends RecyclerView.Adapter<ImgurAdapter.ViewHolder> {

    private Context context;
    private List<Topic> topics = Collections.emptyList();

    public ImgurAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Topic> topics) {
        this.topics = topics;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mvp_imgur, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewItem.setText(getItem(position).getName());
    }

    public Topic getItem(int position) {
        return topics.get(position);
    }

    @Override
    public int getItemCount() {
        return topics.size();
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
