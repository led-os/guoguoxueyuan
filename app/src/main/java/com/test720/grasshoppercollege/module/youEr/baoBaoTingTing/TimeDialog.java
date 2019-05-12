package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.BottomListDialog;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

import butterknife.BindView;
import butterknife.OnClick;

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
 * 作者：水东流 编于 2018/8/24
 */
public class TimeDialog extends BottomListDialog<String> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView statua;
    Switch sw;


    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.rel_list;
    }

    @Override
    public void start() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getList().clear();
        getList().add("当前故事后停止播放");
        getList().add("1分钟后停止播放");
        getList().add("15分钟后停止播放");
        getList().add("30分钟后停止播放");
        getList().add("60分钟后停止播放");
        getList().add("90分钟后停止播放");

        setAdapter(new MyBaseRecyclerAdapter<String>(getList(), getActivity(), R.layout.dao_ji_shi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
                TextView textView = holder.getView(R.id.text);
                if (MusicServiceData.getInstance().getDownStr().equals(item)) {
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                    holder.getView(R.id.gou).setVisibility(View.VISIBLE);
                } else {
                    textView.setTextColor(getResources().getColor(R.color.gwe));
                    holder.getView(R.id.gou).setVisibility(View.GONE);
                }
            }
        });
        View head = LayoutInflater.from(getContext()).inflate(R.layout.time_head, null);
        statua = head.findViewById(R.id.statua);
        sw = head.findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statua.setText("开启");
                } else {
                    statua.setText("关闭");
                }
            }
        });

        getAdapter().setHeaderView(head);
        @SuppressLint("InflateParams") View foot = LayoutInflater.from(getContext()).inflate(R.layout.time_foot, null);
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        getAdapter().setmFoot(foot);
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (sw.isChecked()) {
                    MusicServiceData.getInstance().setDownStr(getList().get(position));
                    switch (position) {
                        case 0:
                            MusicServiceData.getInstance().setNeedNext(false);
                            break;
                        case 1:
                            MusicServiceData.getInstance().downTime(60, mContext);
                            break;
                        case 2:
                            MusicServiceData.getInstance().downTime(60 * 15, mContext);
                            break;
                        case 3:
                            MusicServiceData.getInstance().downTime(60 * 30, mContext);
                            break;
                        case 4:
                            MusicServiceData.getInstance().downTime(60 * 60, mContext);
                            break;
                        case 5:
                            MusicServiceData.getInstance().downTime(60 * 90, mContext);
                            break;
                    }
                    LogUtil.logError("倒计时开始" + 30);
                }
                dismissAllowingStateLoss();
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick(R.id.re1)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }
}
