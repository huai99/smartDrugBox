package com.siehuai.smartdrugbox.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.siehuai.smartdrugbox.R;

import java.util.ArrayList;

public class ReminderListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> mParentList;
    private ExpandableListView mExpandableListView;
    private int lastExpandedGroupPosition;
    private Context mContext;

    public ReminderListViewAdapter(Context context, ExpandableListView expandableListView, ArrayList<String> parentList) {
        mContext = context;
        mExpandableListView = expandableListView;
        mParentList = parentList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return mParentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParentList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.design_parent_list_view_reminder,parent,false);
        }
        convertView.setTag(this.getGroup(groupPosition).toString());

        TextView textView = (TextView) convertView.findViewById(R.id.textView_parent_list);

        textView.setText(this.getGroup(groupPosition).toString());

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.design_child_list_view_reminder,parent,false);
        }
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        if(groupPosition != lastExpandedGroupPosition){
            mExpandableListView.collapseGroup(lastExpandedGroupPosition);
        }
        super.onGroupExpanded(groupPosition);

        lastExpandedGroupPosition=groupPosition;
    }
}
