package com.test720.grasshoppercollege.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.huoDong.bean.ActivityData;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/12
 */
public class HeadGridViewAdapter extends BaseAdapter {
    private List<ActivityData.DataBean.PersonnelBean> personnel;
    Context context;

    public HeadGridViewAdapter(List<ActivityData.DataBean.PersonnelBean> personnel, Context context) {
        this.personnel = personnel;
        this.context = context;
    }

    @Override
    public int getCount() {
        return personnel.size();
    }

    @Override
    public ActivityData.DataBean.PersonnelBean getItem(int position) {
        return personnel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pei_yin_pai_hang_pic_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) itemListener.itemClick();
            }
        });
        viewHolder.name.setText(personnel.get(position).getNickname());
        GlideUntil.getInstance().headByUrl(context, viewHolder.img, personnel.get(position).getHeader());
        viewHolder.num.setVisibility(View.GONE);
        return convertView;
    }

    public interface ItemListener {
        void itemClick();
    }

    ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    static class ViewHolder {
        @BindView(R.id.item)
        LinearLayout item;
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
