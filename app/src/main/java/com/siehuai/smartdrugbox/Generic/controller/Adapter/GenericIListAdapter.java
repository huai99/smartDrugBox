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
    private ArrayList<String> mUserNameList = new ArrayList<>();
    private ArrayList<String> mContactList = new ArrayList<>();
    private ArrayList<String> mMedicineList = new ArrayList<>();
    private ArrayList<String> mAvailabilityList = new ArrayList<>();

    private ListAdapterObservable mClickListenerObservable = new ListAdapterObservable();


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView mUserNameText;
        TextView mContactText;
        TextView mMedicineText;
        TextView mAvailabilityText;
        int position;

        ViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            mUserNameText = (TextView) itemView.findViewById(R.id.text_userName);
            mContactText = (TextView) itemView.findViewById(R.id.text_contact);
            mMedicineText = (TextView) itemView.findViewById(R.id.text_medicine);
            mAvailabilityText = (TextView) itemView.findViewById(R.id.text_availability);
        }

        void setPosition(int position) {
            this.position = position;
        }

        protected void setOnClickListener(View.OnClickListener listener) {
            mView.setOnClickListener(listener);
        }

        void setUserNameText(String userName) {
            mUserNameText.setText(userName);
        }


        void setContactText(String contact) {
            mContactText.setText(contact);
        }

        void setMedicineText(String medicine) {
            mMedicineText.setText(medicine);
        }

        void setAvailabilityText(String availability) {
            mAvailabilityText.setText(availability);
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
        holder.setUserNameText(mUserNameList.get(position));
        holder.setContactText(mContactList.get(position));
        holder.setMedicineText(mMedicineList.get(position));
        holder.setAvailabilityText(mAvailabilityList.get(position));
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
        mUserNameList = mIListResource.getResourceTextMap().get(ListResourceFieldConst.USERNAME);
        mContactList = mIListResource.getResourceTextMap().get(ListResourceFieldConst.CONTACT);
        mMedicineList = mIListResource.getResourceTextMap().get(ListResourceFieldConst.MEDICINE);
        mAvailabilityList = mIListResource.getResourceTextMap().get(ListResourceFieldConst.AVAILABILITY);
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
