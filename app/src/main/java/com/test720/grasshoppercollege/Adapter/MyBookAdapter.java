package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
 * Created by 水东流 on 2018/3/14 0014.
 */

public abstract class MyBookAdapter<T> extends RecyclerView.Adapter<MyBookAdapter.ViewHolder> {
    List<T> list = new ArrayList<>();
    Context context;
    boolean isSet = false;

    public void setSet(boolean set) {
        isSet = set;
    }

    public MyBookAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    static final int ONE = 1;
    static final int TWO = 2;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ONE) {
            view = LayoutInflater.from(context).inflate(R.layout.book_item_three, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.book_item_two, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int pos = position * 2;
        /*如果剩下的数据不足以放一排，则隐藏第二个界面*/
        if (list.size() > (pos + 1)) {
            holder.two.setVisibility(View.VISIBLE);
            holder.twoDown.setVisibility(View.VISIBLE);
            holder.twoUp.setVisibility(View.VISIBLE);
        } else {
            holder.two.setVisibility(View.GONE);
            holder.twoDown.setVisibility(View.GONE);
            holder.twoUp.setVisibility(View.GONE);
            holder.two_sc.setVisibility(View.GONE);
        }
        /*左边的删除建*/
        if (isSet) {
            holder.one_sc.setVisibility(View.VISIBLE);
        } else {
            holder.one_sc.setVisibility(View.GONE);
        }
        /*右边的删除键*/
        if (isSet && list.size() > (pos + 1)) {
            holder.two_sc.setVisibility(View.VISIBLE);
        } else {
            holder.two_sc.setVisibility(View.GONE);
        }

        holder.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookClick(pos);
            }
        });
        if (list.size() > (pos + 1)) {
            holder.two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookClick(pos + 1);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        int size = list.size();
        int yu = size % 2;
        if (yu == 0) {
            return size / 2;
        } else {
            return size / 2 + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ONE : TWO;
    }


    public abstract void bookClick(int position);


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.one)
        public ImageView one;
        @BindView(R.id.one_up)
        public TextView oneUp;
        @BindView(R.id.one_down)
        public TextView oneDown;
        @BindView(R.id.two)
        public ImageView two;
        @BindView(R.id.two_up)
        public TextView twoUp;
        @BindView(R.id.two_down)
        public TextView twoDown;
        @BindView(R.id.back)
        public ImageView back;
        @BindView(R.id.one_sc)
        public ImageView one_sc;
        @BindView(R.id.two_sc)
        public ImageView two_sc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
