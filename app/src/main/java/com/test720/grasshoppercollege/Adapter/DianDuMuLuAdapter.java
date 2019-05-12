package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;

import static com.test720.grasshoppercollege.R.id.unit;

/**
 * Created by 水东流 on 2018/4/23.
 */

public class DianDuMuLuAdapter extends BaseExpandableListAdapter {
    BookDataBean data;
    Context context;
    /**********当前正展示的单元页**********/
    String unitStr;
    String lessonStr;

    public DianDuMuLuAdapter(BookDataBean data, Context context, String unitStr, String lessonStr) {
        this.data = data;
        this.context = context;
        this.unitStr = unitStr;
        this.lessonStr = lessonStr;
    }

    @Override
    public int getGroupCount() {
        return data.getData().getContent().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.getData().getContent().get(groupPosition).getLesson().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.getData().getContent().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.getData().getContent().get(groupPosition).getLesson().get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dian_du_mu_lu_father_item, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.unit = convertView.findViewById(unit);
            groupViewHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.unit.setText(data.getData().getContent().get(groupPosition).getUnit());
        /*******如果是当前正展示的界面，则显示选择背景**/
        if (unitStr.equals(data.getData().getContent().get(groupPosition).getUnit())) {
            groupViewHolder.unit.setBackgroundResource(R.color.bantoumingblue);
        } else {
            groupViewHolder.unit.setBackgroundResource(R.color.white);
        }

        if (isExpanded) {
            groupViewHolder.img.setImageResource(R.mipmap.book_down_biao);
        } else {
            groupViewHolder.img.setImageResource(R.mipmap.book_you);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dain_du_mu_lu_children_item, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.lesson = convertView.findViewById(R.id.lesson);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.lesson.setText(data.getData().getContent().get(groupPosition).getLesson().get(childPosition).getLesson());
        /*******如果是当前正展示的界面，则显示选择背景**/
        if (lessonStr.equals(data.getData().getContent().get(groupPosition).getLesson().get(childPosition).getLesson())
                && unitStr.equals(data.getData().getContent().get(groupPosition).getUnit())) {
            childViewHolder.lesson.setBackgroundResource(R.color.bluebantoumingtwo);
        } else {
            childViewHolder.lesson.setBackgroundResource(R.color.white);
        }
        return convertView;
    }

    /*放开item点击*/
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 组元素绑定器
     */
    private class GroupViewHolder {
        ImageView img;
        TextView unit;
    }

    /**
     * 子元素绑定器
     */
    public class ChildViewHolder {
        TextView lesson;
    }
}
