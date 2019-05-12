package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by 水东流 on 2018/1/16 0016.
 */

public abstract class MyBaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {
    private int itemLayoutId;//布局id
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_HEADERTWO = 2;
    private static final int TYPE_FOOTER = 3;
    private Context context;//上下文
    private List<T> list;//数据源
    private LayoutInflater inflater;//布局器
    private View mHeaderView;
    private View mHeaderViewTwo;
    private View mFoot;

    private OnItemClickListener listener;//点击事件监听器

    private boolean isScrolling = false;
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    public Context getContext() {
        return context;
    }

    public void setmHeadViewTwo(View mHeaderViewTwo) {
        this.mHeaderViewTwo = mHeaderViewTwo;
        notifyItemInserted(1);
    }

    public void setmFoot(View mFoot) {
        this.mFoot = mFoot;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getmHeaderView() {
        return mHeaderView;
    }

    public MyBaseRecyclerAdapter(List<T> list, Context context, int itemLayoutId) {
        this.list = list;
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mHeaderViewTwo == null && mFoot == null) return TYPE_NORMAL;

        if (mHeaderView != null && position == 0) return TYPE_HEADER;

        if (mHeaderView != null && mHeaderViewTwo != null && position == 1) return TYPE_HEADERTWO;

        if (mHeaderView == null && mHeaderViewTwo != null && position == 0) return TYPE_HEADERTWO;

        if (mHeaderViewTwo != null && mHeaderView != null && position > list.size() + 1)
            return TYPE_FOOTER;

        if (mHeaderViewTwo == null && mHeaderView != null && position > list.size())
            return TYPE_FOOTER;

        if (mHeaderViewTwo != null && mHeaderView == null && position > list.size())
            return TYPE_FOOTER;

        if (mHeaderViewTwo == null && mHeaderView == null && position > list.size() - 1)
            return TYPE_FOOTER;

        return TYPE_NORMAL;
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return BaseRecyclerHolder.getRecyclerHolder(context, mHeaderView);
        if (mHeaderViewTwo != null && viewType == TYPE_HEADERTWO)
            return BaseRecyclerHolder.getRecyclerHolder(context, mHeaderViewTwo);
        if (mFoot != null && viewType == TYPE_FOOTER)
            return BaseRecyclerHolder.getRecyclerHolder(context, mFoot);
        View view = inflater.inflate(itemLayoutId, parent, false);
        return BaseRecyclerHolder.getRecyclerHolder(context, view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_HEADERTWO) return;
        if (getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(holder);
        convert(holder, list.get(pos), pos);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && view != null) {
                    listener.onItemClick(pos);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list == null) return 0;
        if (mHeaderView == null && mHeaderViewTwo == null && mFoot == null) {
            return list.size();
        }
        int postion = list.size();
        if (mHeaderView != null) {
            postion++;
        }
        if (mHeaderViewTwo != null) {
            postion++;
        }
        if (mFoot != null) {
            postion++;
        }
        return postion;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        int real = position;
        if (mHeaderView != null) {
            real--;
        }
        if (mHeaderViewTwo != null) {
            real--;
        }
        return real;
    }

    /**
     * @param holder  ViewHolder
     * @param item    子项
     * @param postion 位置
     */
    public abstract void convert(BaseRecyclerHolder holder, T item, int postion);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 定义一个点击事件接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
