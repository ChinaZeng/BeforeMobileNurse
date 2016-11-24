package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseRecyAdapter;
import com.shine.mobilenurse.entity.Option;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzw on 2016/11/22.
 * 描述:
 */

public class MainFragmentMidAdapter extends BaseRecyAdapter<Option> {


    public MainFragmentMidAdapter(Activity context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainFragmentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_cen, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MainFragmentViewHolder) {
            final MainFragmentViewHolder mainFragmentViewHolder = (MainFragmentViewHolder) holder;
            final Option option = mData.get(position);
            mainFragmentViewHolder.textTv.setText(option.getName());
            mainFragmentViewHolder.textIv.setImageResource(R.mipmap.ic_launcher);
            mainFragmentViewHolder.textCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyItemClickListener != null)
                        onRecyItemClickListener.OnItemClick(mainFragmentViewHolder.textCardView, option, position);
                }
            });
        }
    }


    class MainFragmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_tv)
        TextView textTv;
        @BindView(R.id.text_cardView)
        CardView textCardView;
        @BindView(R.id.text_iv)
        ImageView textIv;

        public MainFragmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}


