package com.siehuai.smartdrugbox.Generic.controller.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;

import java.util.ArrayList;
import java.util.Observer;


public abstract class GenericMenuAdapter extends RecyclerView.Adapter<GenericMenuAdapter.ViewHolder>
        implements MenuAdapter {

    public MenuResource mMenuResource;

    ArrayList<Bitmap> mImgResourceList;

    ArrayList<String> mTextResourceList;

    public View.OnClickListener mOnClickListener;

    public MenuAdapterObservable mClickListenerObservable = new MenuAdapterObservable();


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

        protected void setImageBitMap(Bitmap bitMap) {
            mImage.setImageBitmap(bitMap);
        }

        protected void setTextView(String text) {
            mTextView.setText(text);
        }

        protected void setPosition(int position) {
            this.position = position;
        }

        protected void setOnClickListener(View.OnClickListener listener) {
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setImageBitMap(mImgResourceList.get(position));
        holder.setTextView(mTextResourceList.get(position));
        holder.setPosition(position);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListenerObservable.setChanged();
                mClickListenerObservable.notifyObservers(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mMenuResource.getResourceSize();
    }


    private void initResource() {
        mImgResourceList = (ArrayList<Bitmap>) mMenuResource.getResourceImgList();
        mTextResourceList = (ArrayList<String>) mMenuResource.getResourceTextList();
    }

    public MenuAdapterObservable getClickListenerObservable() {
        return mClickListenerObservable;
    }

    public void addObserver(Observer o) {
        mClickListenerObservable.addObserver(o);
    }

    public void deleteObserver(Observer o){
        mClickListenerObservable.deleteObserver(o);
    }

}
