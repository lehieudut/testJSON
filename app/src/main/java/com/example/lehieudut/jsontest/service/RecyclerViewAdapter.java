package com.example.lehieudut.jsontest.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lehieudut.jsontest.Base.BaseAdapter;
import com.example.lehieudut.jsontest.Base.ImageModel;
import com.example.lehieudut.jsontest.R;

import java.util.List;

/**
 * Created by PC on 11/5/2016.
 */

public class RecyclerViewAdapter extends BaseAdapter {

    private List<ImageModel> mArray;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<ImageModel> array) {
        super(context);
        this.mContext = context;
        this.mArray = array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindImageHolder((ImageHolder) holder, position);
    }

    private void bindImageHolder(ImageHolder imageHolder, int position) {
        ImageModel imageModel = mArray.get(position);
        imageHolder.mTextView.setText(imageModel.getName());
        Glide.with(mContext).load(imageModel.getImage()).into(imageHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mTextView;

        public ImageHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.mImage);
            mTextView = (TextView) itemView.findViewById(R.id.mTextView);
        }
    }
}
