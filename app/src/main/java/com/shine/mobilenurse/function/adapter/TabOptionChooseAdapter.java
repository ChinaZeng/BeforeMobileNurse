package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseRecyAdapter;
import com.shine.mobilenurse.entity.Option;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzw on 2016/11/25.
 * 描述:
 */

public class TabOptionChooseAdapter extends BaseRecyAdapter<Option> {

    private int choosePos;

    public TabOptionChooseAdapter(Activity context, int choosePos) {
        super(context);
        this.choosePos = choosePos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabOptionChooseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_taboption_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof TabOptionChooseViewHolder) {
            final TabOptionChooseViewHolder tabOptionChooseViewHolder = (TabOptionChooseViewHolder) holder;
            final Option option = getItem(position);
            if (choosePos == position) {
                tabOptionChooseViewHolder.ietmTaboptionChoText.setTextColor(Color.WHITE);
                tabOptionChooseViewHolder.ietmTaboptionChoText.setBackgroundResource(R.drawable.pop_taboption_cho_bg);
            } else {
                tabOptionChooseViewHolder.ietmTaboptionChoText.setTextColor(Color.BLACK);
                tabOptionChooseViewHolder.ietmTaboptionChoText.setBackgroundResource(R.drawable.pop_taboption_not_cho_bg);
            }

            tabOptionChooseViewHolder.ietmTaboptionChoText.setText(option.getName());
            tabOptionChooseViewHolder.ietmTaboptionChoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyItemClickListener != null) {
                        onRecyItemClickListener.OnItemClick(tabOptionChooseViewHolder.ietmTaboptionChoText, option, position);
                    }
                }
            });
        }
    }

    public void setChoosePos(int choosePos) {
        int temp = this.choosePos;
        this.choosePos = choosePos;
        notifyItemChanged(temp);
        notifyItemChanged(choosePos);
    }


    class TabOptionChooseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ietm_taboption_cho_text)
        TextView ietmTaboptionChoText;

        public TabOptionChooseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
