package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.CiDaiNeiRongData;
import com.test720.grasshoppercollege.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/22.
 */

public abstract class CiDaiUnitAdapter extends BaseExpandableListAdapter {
    List<CiDaiNeiRongData.DataBean.ListBean> list;
    Context context;
    private int fatherPos = -1;//当前父标
    private int childrenPos = -1;//当前子标

    public void setFatherPos(int fatherPos) {
        this.fatherPos = fatherPos;
    }

    public void setChildrenPos(int childrenPos) {
        this.childrenPos = childrenPos;
    }

    protected CiDaiUnitAdapter(List<CiDaiNeiRongData.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getPage().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getPage().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder gholder;
        if (convertView == null) {
            gholder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.ci_dai_father_item, null);
            gholder.title = convertView.findViewById(R.id.title);
            gholder.parentIv = convertView.findViewById(R.id.unitPlay);
            gholder.item = convertView.findViewById(R.id.item);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }

        //如果是当前播放媒体，则添加背景
        if (fatherPos == groupPosition && childrenPos == -1) {
            gholder.item.setBackgroundResource(R.color.bantoumingblue);
        } else {
            gholder.item.setBackgroundResource(R.color.touming);
        }
        gholder.parentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatherPos = groupPosition;
                childrenPos = -1;
                fatherClick(groupPosition);
                notifyDataSetChanged();
                EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.CIDAIPLAYUNTIL, "ok"));
            }
        });
        gholder.title.setText(list.get(groupPosition).getUnit());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grade, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.textView = convertView.findViewById(R.id.gradetext);
            childViewHolder.suo = convertView.findViewById(R.id.suo);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //根据当前标示，设置背景
        if (childPosition == childrenPos && fatherPos == groupPosition) {
            childViewHolder.textView.setBackgroundResource(R.color.bantoumingblue);
        } else {
            childViewHolder.textView.setBackgroundResource(R.color.touming);
        }
        childViewHolder.suo.setImageResource(R.mipmap.laba);
        childViewHolder.textView.setText(list.get(groupPosition).getPage().get(childPosition).getPage());
        childViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childrenPos = childPosition;
                fatherPos = groupPosition;
                childrenClick(groupPosition, childPosition);
                notifyDataSetChanged();
                EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.CIDAIPLAYUNTIL, "ok"));
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    /*添加点击接口*/

    public abstract void childrenClick(int groupPosition, int childPosition);

    public abstract void fatherClick(int groupPosition);

    /**
     * 组元素绑定器
     */
    private class GroupViewHolder {
        LinearLayout item;
        LinearLayout parentIv;
        TextView title;
    }

    /**
     * 子元素绑定器
     */
    public class ChildViewHolder {
        TextView textView;
        ImageView suo;
    }
}
