package com.test720.grasshoppercollege.module.userData.geRenZiLiao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.HttpBean.HeaderData;
import com.test720.grasshoppercollege.R;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/9.
 */

public class HeaderAdapter extends RecyclerView.Adapter<BaseRecyclerHolder> {
    private final static int ONE = 1;//获得
    private final static int TWO = 2;//空格
    private final static int THREE = 3;//以获得item
    private final static int FOUR = 4;//未获得
    private final static int FIVE = 5;//未获得item
    private final static int SIX = 6;//未获得空格
    private int index = 0;//当前选中的头像框
    private List<HeaderData.DataBean.NoObtainBean> list;//未获得
    private List<HeaderData.DataBean.ObtainBean> list1;//以获得
    private Context context;
    private ImgClick imgClick;

    public void setImgClick(ImgClick imgClick) {
        this.imgClick = imgClick;
    }

    HeaderAdapter(List<HeaderData.DataBean.NoObtainBean> list, List<HeaderData.DataBean.ObtainBean> list1, Context context) {
        this.list = list;
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        float f = list1.size() / 3f;//以获得的集合
        double i = Math.ceil(f);//?整:Math.ceil(2.1)=3

        if (position == 0) {
            return ONE;
        } else if (position == 1 || position == 2) {
            return TWO;
        } else if (position - 3 < list1.size()) {
            return THREE;
        } else if (position - 3 >= list1.size() && (position - 3) < (i * 3)) {
            return TWO;
        } else if (position == (i * 3 + 3)) {
            return FOUR;
        } else if (position < (i * 3 + 3 + 3)) {
            return SIX;
        } else {
            return FIVE;
        }
    }

    @NonNull
    @Override
    public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ONE:
                view = LayoutInflater.from(context).inflate(R.layout.header_item_one, parent, false);
                break;
            case TWO:
                view = LayoutInflater.from(context).inflate(R.layout.header_item_two, parent, false);
                break;
            case THREE:
                view = LayoutInflater.from(context).inflate(R.layout.header_item_three, parent, false);
                break;
            case FOUR:
                view = LayoutInflater.from(context).inflate(R.layout.header_item_four, parent, false);
                break;
            case FIVE:
                view = LayoutInflater.from(context).inflate(R.layout.header_item, parent, false);
                break;
            case SIX:
                view = LayoutInflater.from(context).inflate(R.layout.header_item_six, parent, false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.header_item, parent, false);
        }
        return BaseRecyclerHolder.getRecyclerHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (getItemViewType(position) == ONE) return;
        if (getItemViewType(position) == TWO) return;
        if (getItemViewType(position) == FOUR) return;
        if (getItemViewType(position) == SIX) return;

        if (getItemViewType(position) == THREE) {//已经获得的头像
            final int pos = position - 3;
            holder.setText(R.id.name, list1.get(pos).getName());
            holder.setHeaderByUrl(R.id.pic, list1.get(pos).getPic());
            if (position == index) {//如果是选中状态则添加背景框
                holder.getView(R.id.lin).setBackgroundResource(R.mipmap.xuanzxuxian);
            } else {
                holder.getView(R.id.lin).setBackgroundResource(R.color.white);
            }
            holder.getView(R.id.pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgClick.ObtainClick(list1.get(pos).getHeader_id(), list1.get(pos).getPic());
                    index = position;
                    notifyDataSetChanged();
                }
            });


        } else {//未获得的头像
            float f = list1.size() / 3f;//以获得的集合
            double i = Math.ceil(f);//?整:Math.ceil(2.1)=3
            final int pos = position - 3 - 3 - (int) (i * 3);
            holder.setText(R.id.doudou, list.get(pos).getPoints() + "果果豆兑换");
            holder.setText(R.id.name, list.get(pos).getName());
            if (position == index) {//如果是选中状态则添加背景框
                holder.getView(R.id.lin).setBackgroundResource(R.mipmap.xuanzxuxian);
            } else {
                holder.getView(R.id.lin).setBackgroundResource(R.color.white);
            }
            //*****/
            holder.setHeaderByUrl(R.id.pic, list.get(pos).getPic());
            RoundedImageView img = holder.getView(R.id.pic);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgClick.NoObtainClick(list.get(pos).getHeader_id(), list.get(pos).getPoints());
                    index = position;
                    notifyDataSetChanged();
                }
            });
            //***设置图片为灰色**/
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0); // 设置饱和度
            ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
            img.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
        }
    }

    public interface ImgClick {
        void ObtainClick(String id, String url);

        void NoObtainClick(String id, String points);
    }

    @Override
    public int getItemCount() {
        float f = list1.size() / 3f;//以获得的集合
        double i = Math.ceil(f);//?整:Math.ceil(2.1)=3
        return 3 + (int) (i * 3) + 3 + list.size();
    }
}
