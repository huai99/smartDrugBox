package com.siehuai.smartdrugbox.controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

import java.util.ArrayList;


public abstract class GenericRenderAdapter extends RecyclerView.Adapter<GenericRenderAdapter.ViewHolder>
        implements MenuAdapter {

    public MenuResource mMenuResource;

    ArrayList<Integer> mImgResourceList;

    ArrayList<String> mTextResourceList;

    public View.OnClickListener mOnClickListener;


    public GenericRenderAdapter() {
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView mImage;
        TextView mTextView;
        int position;


        protected ViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            mImage = (ImageView) itemView.findViewById(R.id.item_image);
            mTextView = (TextView) itemView.findViewById(R.id.item_text);
        }

        protected void setImageSource(int resId) {
            mImage.setImageResource(resId);
        }

        protected void setTextView(String text) {
            mTextView.setText(text);
        }

        protected void setPosition(int position) {
            this.position = position;
        }

        protected void setOnClickListener(View.OnClickListener listener){
            mView.setOnClickListener(listener);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_grid_view, parent, false);
        initResource();
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setImageSource(mImgResourceList.get(position));
        holder.setTextView(mTextResourceList.get(position));
        holder.setPosition(position);
        holder.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mMenuResource.getResourceSize();
    }


    private void initResource() {
        mImgResourceList = (ArrayList<Integer>) mMenuResource.getResourceImgList();
        mTextResourceList = (ArrayList<String>) mMenuResource.getResourceTextList();
    }

}
