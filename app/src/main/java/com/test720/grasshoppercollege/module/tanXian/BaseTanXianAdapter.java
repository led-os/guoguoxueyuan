package com.test720.grasshoppercollege.module.tanXian;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TanXianDialog.TomorrowComingDialog;
import com.test720.grasshoppercollege.module.tanXian.bean.TanXianGuanKaData;
import com.test720.grasshoppercollege.myViews.TanXianGuankaView;

import java.util.ArrayList;
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
 * 作者：水东流 编于 2018/10/25
 */
public abstract class BaseTanXianAdapter extends RecyclerView.Adapter<BaseTanXianAdapter.ViewHolder> {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    List<List<TanXianGuanKaData.DataBean>> list = new ArrayList();
    AppCompatActivity context;

    public BaseTanXianAdapter(List<List<TanXianGuanKaData.DataBean>> list, AppCompatActivity context) {
        this.list = list;
        this.context = context;
    }

    public List<List<TanXianGuanKaData.DataBean>> getList() {
        return list;
    }

    @LayoutRes
    public abstract int onelayout();

    @LayoutRes
    public abstract int twolayout();

    @LayoutRes
    public abstract int threelayout();

    @LayoutRes
    public abstract int fourlayout();

    /**
     * 点击关卡，跳转到题目
     */
    public void jumToTiMu(TanXianGuanKaData.DataBean bean) {
        if (bean.getEnter() == 1) {
            Intent intent = new Intent(context, TanXianTiMuActivity.class);
            intent.putExtra("checkpoint_id", bean.getCheckpoint_id());
            context.startActivity(intent);
        } else {
            TomorrowComingDialog tomorrowComingDialog = new TomorrowComingDialog();
            tomorrowComingDialog.show(context.getSupportFragmentManager(), "tomorrow");
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ONE:
                view = LayoutInflater.from(context).inflate(onelayout(), parent, false);
                break;
            case TWO:
                view = LayoutInflater.from(context).inflate(twolayout(), parent, false);
                break;
            case THREE:
                view = LayoutInflater.from(context).inflate(threelayout(), parent, false);
                break;
            case FOUR:
                view = LayoutInflater.from(context).inflate(fourlayout(), parent, false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(onelayout(), parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseTanXianAdapter.ViewHolder holder, int position) {
        for (int i = 0; i < getList().get(position).size(); i++) {
            final TanXianGuanKaData.DataBean bean = getList().get(position).get(i);
            switch (i) {
                case 0:
                    if (bean.getOpen_up() == 1) {
                        holder.one.setSuoB(false);
                        holder.one.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumToTiMu(bean);
                            }
                        });
                    } else {
                        holder.one.setSuoB(true);
                    }
                    break;
                case 1:
                    if (bean.getOpen_up() == 1) {
                        holder.two.setSuoB(false);
                        holder.two.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumToTiMu(bean);
                            }
                        });
                    } else {
                        holder.two.setSuoB(true);
                    }
                    break;
                case 2:
                    if (bean.getOpen_up() == 1) {
                        holder.three.setSuoB(false);
                        holder.three.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumToTiMu(bean);
                            }
                        });
                    } else {
                        holder.three.setSuoB(true);
                    }
                    break;
                case 3:
                    if (bean.getOpen_up() == 1) {
                        holder.four.setSuoB(false);
                        if (bean.getEnter() == 1) {
                            holder.four.getImg().setImageResource(R.drawable.bx11);
                        } else {
                            holder.four.getImg().setImageResource(R.drawable.bao_xiang_po);
                        }
                        holder.four.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (bean.getEnter() != 1) {
                                    if (openBox != null)
                                        openBox.openBox(bean.getCheckpoint_id(), holder.four.getImg());
                                }
                            }
                        });
                    } else {
                        holder.four.setSuoB(true);
                        holder.four.getImg().setImageResource(R.drawable.bx1);
                    }

                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() == 1) {//页码一页
            if (position == 0) return ONE;
        } else if (list.size() == 2) {//页码二页
            if (position == 0) return TWO;
            if (position == 1) return ONE;
        } else if (list.size() == 3) {//页码三页
            if (position == 0) return THREE;
            if (position == 1) return TWO;
            if (position == 2) return ONE;
        } else if (list.size() == 4) {//页码三页
            if (position == 0) return FOUR;
            if (position == 1) return THREE;
            if (position == 2) return TWO;
            if (position == 3) return ONE;
        } else {
            if (position == 0) return FOUR;
            if (position == list.size() - 1) return ONE;
            int size = list.size();
            int yu = size % 2;
            switch (yu) {
                case 0:
                    int y = position % 2;
                    if (y == 0) return TWO;
                    else return THREE;
                case 1:
                    int x = position % 2;
                    if (x == 0) return THREE;
                    else return TWO;
            }
        }
        return ONE;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 开箱子接口，需在外部实现
     */
    public interface OpenBox {
        void openBox(String id, ImageView imageView);
    }

    OpenBox openBox;


    public void setOpenBox(OpenBox openBox) {
        this.openBox = openBox;
    }

    public OpenBox getOpenBox() {
        return openBox;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.four)
        public TanXianGuankaView four;
        @BindView(R.id.three)
        public TanXianGuankaView three;
        @BindView(R.id.two)
        public TanXianGuankaView two;
        @BindView(R.id.one)
        public TanXianGuankaView one;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

