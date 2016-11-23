package com.shine.mobilenurse.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.shine.mobilenurse.function.OnRecyItemClickListener;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public abstract class BaseRecyAdapter<Item> extends RecyclerView.Adapter {

    protected List<Item> mData;

    protected Activity context;

    protected OnRecyItemClickListener<Item> onRecyItemClickListener;

    public void setOnRecyItemClickListener(OnRecyItemClickListener<Item> onRecyItemClickListener) {
        this.onRecyItemClickListener = onRecyItemClickListener;
    }

    public BaseRecyAdapter(Activity context) {
        this.context = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public Item getItem(int pos) {
        return pos > mData.size() - 1 ? null : mData.get(pos);
    }

    public void addItem(Item item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    public void addItemToTop(Item item) {
        mData.add(0, item);
        notifyItemInserted(0);
    }

    public void addItems(List<Item> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItemsToTop(List<Item> items) {
        mData.addAll(0, items);
        notifyItemInserted(0);
    }

    public void updateItem(int pos, Item item) {
        if (mData != null && mData.size() > pos) {
            mData.set(pos, item);
            notifyItemChanged(pos);
        }
    }

    public void removeItem(int pos, Item item) {
        mData.remove(item);
        notifyItemRemoved(pos);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}

