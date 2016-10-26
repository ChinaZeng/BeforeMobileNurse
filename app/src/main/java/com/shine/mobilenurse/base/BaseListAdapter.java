package com.shine.mobilenurse.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseListAdapter<Item> extends BaseAdapter {

    protected final List<Item> mData = new ArrayList<Item>();

    protected Activity context;

    public BaseListAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Item getItem(int position) {
        if (position >= 0 && position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    public void addItem(Item item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addItemToTop(Item item) {
        mData.add(0, item);
        notifyDataSetChanged();
    }

    public void addItems(List<Item> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItemsToTop(List<Item> items) {
        mData.addAll(0, items);
        notifyDataSetChanged();
    }

    public void updateItem(int pos, Item item) {
        if (mData != null && mData.size() > pos) {
            mData.set(pos, item);
            notifyDataSetChanged();
        }
    }

    public void removeItem(Item item) {
        mData.remove(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

}
