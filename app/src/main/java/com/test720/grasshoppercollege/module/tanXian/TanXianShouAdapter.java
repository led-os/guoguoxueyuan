package com.test720.grasshoppercollege.module.tanXian;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.fragment.TanXianListFragment;
import com.test720.grasshoppercollege.module.tanXian.bingChuan.BingChuanShouActivity;
import com.test720.grasshoppercollege.module.tanXian.caoYuan.CaoYuanShouActivity;
import com.test720.grasshoppercollege.module.tanXian.diDi.DiDiShouActivity;
import com.test720.grasshoppercollege.module.tanXian.haiDi.HaiDiShouActivity;
import com.test720.grasshoppercollege.module.tanXian.reDai.ReDaiShouActivity;
import com.test720.grasshoppercollege.module.tanXian.shaMo.ShaMoShouActivity;
import com.test720.grasshoppercollege.module.tanXian.shengLin.ShengLinShouActivity;
import com.test720.grasshoppercollege.module.tanXian.xingkong.XingKongShouActivity;
import com.test720.grasshoppercollege.myViews.TanXianGuankaView;

import java.util.List;
import java.util.Objects;

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
 * 作者：水东流 编于 2018/12/11
 */
public class TanXianShouAdapter extends RecyclerView.Adapter<TanXianShouAdapter.ViewHolder> {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    Activity context;
    private List<TanXianListFragment.Data.DataBean> list;

    public TanXianShouAdapter(Activity context, List<TanXianListFragment.Data.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 点击关卡，跳转到题目
     */

    public void jumpToActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(context).overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
            if (finish) {
                context.finish();
            }
        }
    }

    /**
     * 跳转
     *
     * @param postion
     */
    private void jumTo(int postion) {
        switch (postion) {
            case 7:
                jumpToActivity(XingKongShouActivity.class, false);
                break;
            case 6:
                jumpToActivity(DiDiShouActivity.class, false);
                break;
            case 5:
                jumpToActivity(ShaMoShouActivity.class, false);
                break;
            case 4:
                jumpToActivity(ReDaiShouActivity.class, false);
                break;
            case 3:
                jumpToActivity(CaoYuanShouActivity.class, false);
                break;
            case 2:
                jumpToActivity(BingChuanShouActivity.class, false);
                break;
            case 1:
                jumpToActivity(ShengLinShouActivity.class, false);
                break;
            case 0:
                jumpToActivity(HaiDiShouActivity.class, false);
                break;
        }
    }


    @NonNull
    @Override
    public TanXianShouAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ONE:
                view = LayoutInflater.from(context).inflate(R.layout.tan_xian_shou_item_one, parent, false);
                break;
            case TWO:
                view = LayoutInflater.from(context).inflate(R.layout.tan_xian_shou_item_two, parent, false);
                break;
            case THREE:
                view = LayoutInflater.from(context).inflate(R.layout.tan_xian_shou_item_three, parent, false);
                break;
            case FOUR:
                view = LayoutInflater.from(context).inflate(R.layout.tan_xian_shou_item_four, parent, false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.tan_xian_shou_item_one, parent, false);
        }
        return new TanXianShouAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TanXianShouAdapter.ViewHolder holder, int position) {
        final int realPos = position * 2;
        //第一个球
        if (list.get(realPos).getOpen_up() == 1) {
            holder.one.setSuoB(false);
            holder.one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumTo(realPos);
                }
            });
        } else {
            holder.one.setSuoB(true);
            holder.one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "请先通关上一关卡", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //第二个球
        if (list.get(realPos + 1).getOpen_up() == 1) {
            holder.two.setSuoB(false);
            holder.two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumTo(realPos + 1);
                }
            });
        } else {
            holder.two.setSuoB(true);
            holder.two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "请先通关上一关卡", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return FOUR;
            case 1:
                return THREE;
            case 2:
                return TWO;
            case 3:
                return ONE;
            default:
                return ONE;
        }
    }

    @Override
    public int getItemCount() {
        return list.size() / 2;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.two)
        TanXianGuankaView one;
        @BindView(R.id.one)
        TanXianGuankaView two;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
