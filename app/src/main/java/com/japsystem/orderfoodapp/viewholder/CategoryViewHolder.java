package com.japsystem.orderfoodapp.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.japsystem.orderfoodapp.ItemClickListener;
import com.japsystem.orderfoodapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.menu_category_image) public ImageView mImageView;
    @BindView(R.id.menu_category_name) public TextView mTextView;

    private ItemClickListener mItemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
