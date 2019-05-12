package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.ExpressionUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 水东流 on 2018/4/18.
 */

public class PinJiaChildrenItem extends BaseAdapter {
    List<PingLunData.ChildBean> list;
    Context context;

    public PinJiaChildrenItem(List<PingLunData.ChildBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ping_lun_children_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getHeader()).error(R.mipmap.pic_head_default).into(viewHolder.head);
        viewHolder.text.setText(list.get(position).getNickname() + ":");
            /*评论内容，包含表情*/
        int size = list.get(position).getContent().length();
        SpannableString spannableString = ExpressionUtil
                .getExpressionString(context, list.get(position).getContent(), size);
        viewHolder.body.setText(spannableString);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.head)
        RoundedImageView head;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.body)
        TextView body;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
