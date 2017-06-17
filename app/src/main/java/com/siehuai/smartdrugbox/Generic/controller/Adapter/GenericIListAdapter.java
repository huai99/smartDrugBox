package com.siehuai.smartdrugbox.Generic.controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.ListResourceFieldConst;
import com.siehuai.smartdrugbox.R;

import java.util.ArrayList;
import java.util.Observer;

public abstract class GenericIListAdapter extends RecyclerView.Adapter<GenericIListAdapter.ViewHolder>
        implements IListAdapter {

    protected IListResource mIListResource;
    private ArrayList<IDbData> mIDbDataList;
    private ArrayList<String> mColumn1List = new ArrayList<>();
    private ArrayList<String> mColumn2List = new ArrayList<>();
    private ArrayList<String> mColumn3List = new ArrayList<>();
    private ArrayList<String> mColumn4List = new ArrayList<>();

    private ListAdapterObservable mClickListenerObservable = new ListAdapterObservable();


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView mColumn1Text;
        TextView mColumn2Text;
        TextView mColumn3Text;
        TextView mColumn4Text;
        int position;

        ViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            mColumn1Text = (TextView) itemView.findViewById(R.id.text_column1);
            mColumn2Text = (TextView) itemView.findViewById(R.id.text_column2);
            mColumn3Text = (TextView) itemView.findViewById(R.id.text_column3);
            mColumn4Text = (TextView) itemView.findViewById(R.id.text_column4);
        }

        void setPosition(int position) {
            this.position = position;
        }

        protected void setOnClickListener(View.OnClickListener listener) {
            mView.setOnClickListener(listener);
        }

        void setColumn1Text(String userName) {
            mColumn1Text.setText(userName);
        }


        void setColumn2Text(String contact) {
            mColumn2Text.setText(contact);
        }

        void setColumn3Text(String medicine) {
            mColumn3Text.setText(medicine);
        }

        void setColumn4Text(String availability) {
            mColumn4Text.setText(availability);
        }
    }

    @Override
    public GenericIListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_list_view, parent, false);
        initResource();
        return new GenericIListAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final GenericIListAdapter.ViewHolder holder, int position) {
        holder.setColumn1Text(mColumn1List.get(position));
        holder.setColumn2Text(mColumn2List.get(position));
        holder.setColumn3Text(mColumn3List.get(position));
        holder.setColumn4Text(mColumn4List.get(position));
        holder.setPosition(position);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListenerObservable.setChanged();
                mClickListenerObservable.notifyObservers(mIDbDataList.get(holder.getAdapterPosition()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mIListResource.getResourceSize();
    }


    private void initResource() {
        mColumn1List = mIListResource.getResourceTextMap().get(ListResourceFieldConst.COLUMN1);
        mColumn2List = mIListResource.getResourceTextMap().get(ListResourceFieldConst.COLUMN2);
        mColumn3List = mIListResource.getResourceTextMap().get(ListResourceFieldConst.COLUMN3);
        mColumn4List = mIListResource.getResourceTextMap().get(ListResourceFieldConst.COLUMN4);
        mIDbDataList = (ArrayList<IDbData>) mIListResource.getResourceList();
    }

    public ListAdapterObservable getClickListenerObservable() {
        return mClickListenerObservable;
    }

    public void addObserver(Observer o) {
        mClickListenerObservable.addObserver(o);
    }

    public void deleteObserver(Observer o) {
        mClickListenerObservable.deleteObserver(o);
    }

}
