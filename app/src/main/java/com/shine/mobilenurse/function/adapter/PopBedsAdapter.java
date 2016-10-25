package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseRecyAdapter;
import com.shine.mobilenurse.entity.Beds;
import com.shine.mobilenurse.utils.ViewUtil;


/**
 * Created by zzw on 2016/10/24.
 * 描述:
 */

public class PopBedsAdapter extends BaseRecyAdapter<Beds> {


//    private OnPopBedsClickListener listener;
//
//    public interface OnPopBedsClickListener {
//        void optionClick(Beds bean, int pos);
//    }
//
//    public void setOptionClickListener(OnPopBedsClickListener listener) {
//        this.listener = listener;
//    }


    public PopBedsAdapter(Activity context) {
        super(context);
    }


    @Override
    public PopBedsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopBedsAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_pop_recy, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PopBedsAdapterViewHolder) {
            PopBedsAdapterViewHolder mHolder = (PopBedsAdapterViewHolder) holder;
            final Beds beds = mData.get(position);
            mHolder.numText.setText(beds.getBedNum() + "");
            mHolder.nameText.setText(beds.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class PopBedsAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView numText, nameText;
        private ImageView imageView;
        private View root;

        public PopBedsAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = ViewUtil.$(itemView, R.id.item_main_pop_recy_image);
            numText = ViewUtil.$(itemView, R.id.item_main_pop_recy_num);
            nameText = ViewUtil.$(itemView, R.id.item_main_pop_recy_name);
            root = ViewUtil.$(itemView, R.id.item_main_pop_recy_root);
        }
    }

}
