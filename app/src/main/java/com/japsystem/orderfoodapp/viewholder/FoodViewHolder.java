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

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.food_name) public TextView mTextView;
    @BindView(R.id.food_image) public ImageView mImageView;

    private ItemClickListener mItemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
