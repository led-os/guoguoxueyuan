package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/18.
 */

public class SendImgAdapter extends BaseAdapter {
    public Context mContext;
    public List<String> mLists;
    callBack mCallBack;

    public SendImgAdapter(Context mContext, List<String> mLists, callBack mCallBack) {
        this.mContext = mContext;
        this.mLists = mLists;
        this.mCallBack = mCallBack;
    }

    public interface callBack {
        void send(View v, int position);
    }
    //用来判断是否是刚刚进入，刚进入只显示添加按钮，也就是上面java代码中只传this的时候

    @Override
    public int getCount() {
        //这里判断数据如果有9张就size等于9,否则就+1，+1是为按钮留的位置
        return mLists.size() < 3 ? mLists.size() + 1 : 3;
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_userphotosrecyler, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.iv_delete = (ImageButton) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position <= mLists.size() - 1) {
            Glide.with(mContext).load(mLists.get(position).trim()).into(holder.mImageView);
            holder.iv_delete.setVisibility(View.VISIBLE);
        } else {
            Glide.with(mContext).load(R.mipmap.jiaimg).into(holder.mImageView);
            holder.iv_delete.setVisibility(View.INVISIBLE);
        }

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.send(v, position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;
        ImageView iv_delete;
    }
}
