package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseRecyAdapter;
import com.shine.mobilenurse.entity.Beds;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzw on 2016/11/23.
 * 描述:
 */

public class MainbedsPupAdapter extends BaseRecyAdapter<Beds> {


    /**
     * 当前选择的床号
     */
    private int chooseBedsPos = 1;

    public MainbedsPupAdapter(Activity context) {
        super(context);
    }

    public MainbedsPupAdapter(Activity context, int chooseBedsPos) {
        super(context);
        this.chooseBedsPos = chooseBedsPos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainBedsPupViewHolder(LayoutInflater.from(context).inflate(R.layout.item_beds_pup, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MainBedsPupViewHolder) {
            final MainBedsPupViewHolder mainBedsPupViewHolder = (MainBedsPupViewHolder) holder;
            final Beds beds = getItem(position);
            if (position == chooseBedsPos) {
                //现在没图
                mainBedsPupViewHolder.mainPupItemIv.setImageResource(R.mipmap.ic_not_choed_beds);
                mainBedsPupViewHolder.mainPupItemBedsname.setTextColor(context.getResources().getColor(R.color.color_beds_choed_text));
                mainBedsPupViewHolder.mainPupItemBedsnum.setTextColor(context.getResources().getColor(R.color.color_beds_choed_text));
                mainBedsPupViewHolder.mainPupItemErji.setBackgroundResource(R.drawable.beds_pup_recy_op_choed_bg);
                mainBedsPupViewHolder.mainPupItemWei.setBackgroundResource(R.drawable.beds_pup_recy_op_choed_bg);
                mainBedsPupViewHolder.mainPupItemGao.setBackgroundResource(R.drawable.beds_pup_recy_op_choed_bg);
            } else {
                mainBedsPupViewHolder.mainPupItemIv.setImageResource(R.mipmap.ic_not_choed_beds);
                mainBedsPupViewHolder.mainPupItemBedsname.setTextColor(context.getResources().getColor(R.color.font_public_gray));
                mainBedsPupViewHolder.mainPupItemBedsnum.setTextColor(context.getResources().getColor(R.color.font_public_gray));
                mainBedsPupViewHolder.mainPupItemErji.setBackgroundResource(R.drawable.beds_pup_recy_op_not_choed_bg);
                mainBedsPupViewHolder.mainPupItemWei.setBackgroundResource(R.drawable.beds_pup_recy_op_not_choed_bg);
                mainBedsPupViewHolder.mainPupItemGao.setBackgroundResource(R.drawable.beds_pup_recy_op_not_choed_bg);
            }

            mainBedsPupViewHolder.mainPupItemBedsname.setText(beds.getName());
            mainBedsPupViewHolder.mainPupItemBedsnum.setText(beds.getBedNum() + "");
            mainBedsPupViewHolder.mainPupItemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyItemClickListener != null)
                        onRecyItemClickListener.OnItemClick(mainBedsPupViewHolder.mainPupItemRoot, beds, position);
                }
            });
        }

    }


    public boolean chooseBeds(int pos) {
        //pos少于总数量  || 点击当前选中
        if (mData.size() < pos || pos == chooseBedsPos)
            return false;
        int tempPos = chooseBedsPos;
        this.chooseBedsPos = pos;

        notifyItemChanged(tempPos);
        notifyItemChanged(pos);
        return true;
    }


    class MainBedsPupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_pup_item_iv)
        ImageView mainPupItemIv;
        @BindView(R.id.main_pup_item_bedsnum)
        TextView mainPupItemBedsnum;
        @BindView(R.id.main_pup_item_bedsname)
        TextView mainPupItemBedsname;
        @BindView(R.id.main_pup_item_wei)
        TextView mainPupItemWei;
        @BindView(R.id.main_pup_item_gao)
        TextView mainPupItemGao;
        @BindView(R.id.main_pup_item_erji)
        TextView mainPupItemErji;
        @BindView(R.id.main_pup_item_root)
        LinearLayout mainPupItemRoot;

        public MainBedsPupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
