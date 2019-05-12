package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.HttpBean.KeBenUnitData;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/21 0021.
 */

public abstract class UntilExpandableListViewAdapter extends BaseExpandableListAdapter {
    List<KeBenUnitData.DataBean> list = new ArrayList<>();
    Context context;

    public UntilExpandableListViewAdapter(List<KeBenUnitData.DataBean> list, Context context) {
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
            convertView = View.inflate(context, R.layout.item_book_unit, null);
            gholder.version = (TextView) convertView.findViewById(R.id.version);
            gholder.parentIv = (ImageView) convertView.findViewById(R.id.iv_parent_group_indicator);
            gholder.item = (RelativeLayout) convertView.findViewById(R.id.item);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        gholder.version.setText(list.get(groupPosition).getUnit());

        if (isExpanded) {
            gholder.parentIv.setImageResource(R.mipmap.book_up_biao);
        } else {
            gholder.parentIv.setImageResource(R.mipmap.book_down_biao);
        }
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
        /*当前关卡是否打开*/
        if (list.get(groupPosition).getPage().get(childPosition).getOpen_up() == 1) {
            childViewHolder.suo.setVisibility(View.GONE);
        } else {
            childViewHolder.suo.setVisibility(View.VISIBLE);
        }
        childViewHolder.textView.setText(list.get(groupPosition).getPage().get(childPosition).getPage());
        childViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeBenUnitData.DataBean.PageBean pageBean = list.get(groupPosition).getPage().get(childPosition);
                childrenClick(pageBean, list.get(groupPosition).getUnit());
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /*添加点击接口*/

    public abstract void childrenClick(KeBenUnitData.DataBean.PageBean pageBean, String unit);

    /**
     * 组元素绑定器
     */
    private class GroupViewHolder {
        RelativeLayout item;
        ImageView parentIv;
        TextView version;
    }

    /**
     * 子元素绑定器
     */
    public class ChildViewHolder {
        TextView textView;
        ImageView suo;
    }
}
