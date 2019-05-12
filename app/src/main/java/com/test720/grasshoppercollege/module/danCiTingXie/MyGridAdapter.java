package com.test720.grasshoppercollege.module.danCiTingXie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.PeiYinData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2018/3/26.
 */

public class MyGridAdapter extends BaseAdapter {
    Context context;
    int index = 0;
    private List<PeiYinData.DataBean.ListBean> list;

    public void setIndex(int index) {
        this.index = index;
    }

    public MyGridAdapter(Context context, List<PeiYinData.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.pei_yin_pai_hang_pic_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(list.get(i).getNickname());
        Glide.with(context).load(list.get(i).getHeader())
                .placeholder(R.mipmap.pic_head_default)
                .error(R.mipmap.pic_head_default)
                .into(viewHolder.img);
        viewHolder.num.setText(list.get(i).getThumb_up_count() + "");
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) itemClick.itemClick(i);
            }
        });
        return view;
    }

    ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void itemClick(int position);
    }

    static class ViewHolder {
        @BindView(R.id.img)
        RoundedImageView img;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.num)
        TextView num;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
