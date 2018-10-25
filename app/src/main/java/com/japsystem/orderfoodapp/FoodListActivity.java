package com.japsystem.orderfoodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.japsystem.orderfoodapp.model.Food;
import com.japsystem.orderfoodapp.viewholder.FoodViewHolder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class FoodListActivity extends AppCompatActivity {

    private static final String EXTRA_CATEGORY_ID = "category_id";

    @BindView(R.id.recycler_food_list) RecyclerView mRecyclerView;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Food, FoodViewHolder> mAdapter;
    private String mCategoryId;

    public static Intent newIntent(Context from, String categoryId) {
        Intent intent = new Intent(from, FoodListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);

        // Get Intent extra
        if (getIntent() != null) {
            mCategoryId = getIntent().getStringExtra(EXTRA_CATEGORY_ID);
        }

        // Init Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("food");

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        if (mCategoryId != null && !mCategoryId.isEmpty()) {
            loadListFood();
        }
    }

    private void loadListFood() {

        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(mDatabaseReference.orderByChild("category_id").equalTo(mCategoryId), Food.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull final Food model) {

                holder.mTextView.setText(model.getName());

                OkHttpClient okHttpClient = new OkHttpClient();
                OkHttp3Downloader downloader = new OkHttp3Downloader(okHttpClient);

                Picasso picasso = new Picasso.Builder(getApplicationContext())
                        .downloader(downloader)
                        .build();

                picasso.load(model.getImage())
                        .error(android.R.drawable.ic_dialog_alert)
                        .into(holder.mImageView);

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodListActivity.this, "Clicked " + model.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_item, viewGroup, false);
                return new FoodViewHolder(view);
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }
}
