package com.shine.mobilenurse.function.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.shine.mobilenurse.base.BaseListAdapter;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.Res;
import com.shine.mobilenurse.view.LogoAndTextView;

/**
 * Created by zzw on 2016/10/26.
 * 描述:
 */

public class OptionGridViewAdapter extends BaseListAdapter<Option> {


    public OptionGridViewAdapter(Activity context) {
        super(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OptionGrideViewHolder holder;
        if (convertView == null) {
            holder = new OptionGrideViewHolder();
            convertView=new LogoAndTextView(context);
            holder.logoAndTextView= (LogoAndTextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (OptionGrideViewHolder) convertView.getTag();
        }

        Option option=getItem(position);
        holder.logoAndTextView.setText(option.getName()+"");
        holder.logoAndTextView.setSrc(Res.getDrawByTag(context,option.getTeg()));
        return convertView;
    }


    class OptionGrideViewHolder{
        public LogoAndTextView logoAndTextView;
    }

}
