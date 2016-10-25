package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseRecyAdapter;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.Res;
import com.shine.mobilenurse.view.LogoAndTextView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zzw on 2016/10/13.
 * 描述:
 */

public class OptionAdapter extends BaseRecyAdapter<Option> {

    public final static int CHILD_W_MATCH=0;
    public final static int CHILD_W_WARP=1;

    private int type;

    public OptionAdapter(Activity context) {
        super(context);
        type=CHILD_W_MATCH;
    }

    public OptionAdapter(Activity context,int type) {
        super(context);
        this.type=type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof OptionViewHolder) {
            final Option bean = mData.get(position);
            OptionViewHolder optionViewHolder = (OptionViewHolder) holder;
            optionViewHolder.TImageBTextView.setText(bean.getName());
            optionViewHolder.TImageBTextView.setSrc(Res.getDrawByTag(context, bean.getTag()));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public OptionAdapter.OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OptionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_options, parent, false));
    }

    class OptionViewHolder extends RecyclerView.ViewHolder {

        private LogoAndTextView TImageBTextView;

        public OptionViewHolder(View itemView) {
            super(itemView);
            TImageBTextView = (LogoAndTextView) itemView.findViewById(R.id.item_op_mid_0_root);
            if(type==CHILD_W_MATCH){
                TImageBTextView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }else {
                TImageBTextView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }


    }


}
