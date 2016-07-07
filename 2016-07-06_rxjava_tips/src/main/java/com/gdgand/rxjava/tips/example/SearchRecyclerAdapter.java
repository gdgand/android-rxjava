package com.gdgand.rxjava.tips.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdgand.rxjava.tips.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchItemViewHolder> {
    private List<SearchItem> items;

    public SearchRecyclerAdapter(List<SearchItem> items) {
        this.items = items;
    }

    public void refreshResults(List<SearchItem> items) {
        this.items = items;
        notifyDataSetChanged();;
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {
        SearchItem item = items.get(position);

        holder.login.setText(item.getLogin());

        ImageView profileImage = holder.profileImage;
        Picasso.with(profileImage.getContext()).load(item.getAvatarUrl()).into(profileImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class SearchItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_image) ImageView profileImage;
        @BindView(R.id.login) TextView login;

        SearchItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> RxUserBus.pub(login.getText().toString()));
        }
    }
}