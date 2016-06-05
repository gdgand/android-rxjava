package com.gdgand.rxjava.rxjavasample.scroll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gdgand.rxjava.rxjavasample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FasterListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_item_fasterlist)
    public TextView tvTitle;

    public FasterListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
