package com.test720.grasshoppercollege.module.gongLue.dingDan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLineXiangQingData;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.KeChengJiHuaListActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

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
 * 作者：水东流 编于 2018/9/11
 */
public class DingDanDialog extends BaseOkDialogFragment {
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.vipPrice)
    TextView vipPrice;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.zhou)
    TagFlowLayout zhou;
    @BindView(R.id.day)
    TagFlowLayout day;

    private List<OnLineXiangQingData.DataBean.WeekBeanX.WeekBean> week = new ArrayList<>();
    TagAdapter dayAdapter;
    TagAdapter weekAdapter;

    int dayInt = 0;//记录日位置
    int weekInt = 0;//记录当前周位置

    String startTime = "";//开始时间
    String endTime = "";//结束时间
    String keyId = "";//课程id
    String type = "";//模块

    List<String> dayStrs = new ArrayList<>();

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
        return R.layout.buy_class_dialog;
    }

    @SuppressLint("InflateParams")
    @Override
    public void start() {
        assert getArguments() != null;
        title.setText(getArguments().getString("title"));
        price.setText("￥" + getArguments().getString("price"));
        if (getArguments().getString("vip_price") != null) {
            String vp = "vip价格:￥" + getArguments().getString("vip_price");
            vipPrice.setText(vp);
        }
        startTime = getArguments().getString("startTime");
        endTime = getArguments().getString("endTime");
        keyId = getArguments().getString("key_id");
        type = getArguments().getString("type");
        updataTimeUi();
        //周数据
        weekAdapter = new TagAdapter<OnLineXiangQingData.DataBean.WeekBeanX.WeekBean>(week) {
            @Override
            public View getView(FlowLayout parent, int position, OnLineXiangQingData.DataBean.WeekBeanX.WeekBean item) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item.getWeek());
                if (position == weekInt) {
                    textView.setBackgroundResource(R.drawable.bule_back_kuang);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.white_back);
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                }
                return view;
            }
        };
        zhou.setAdapter(weekAdapter);
//每日数据
        dayAdapter = new TagAdapter<String>(dayStrs) {
            @Override
            public View getView(FlowLayout parent, int position, String item) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item);
                if (position == dayInt) {
                    textView.setBackgroundResource(R.drawable.bule_back_kuang);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.white_back);
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                }
                return view;
            }
        };
        day.setAdapter(dayAdapter);

        event();

    }

    /**
     * 事件
     */
    private void event() {
        zhou.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                weekInt = i;
                dayInt = 0;
                updataTimeUi();
                weekAdapter.notifyDataChanged();
                dayStrs.clear();
                dayStrs.addAll(week.get(i).getTime_slot());
                dayAdapter.notifyDataChanged();
                return true;
            }
        });
        day.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                dayInt = position;
                dayAdapter.notifyDataChanged();
                updataTimeUi();
                return true;
            }
        });
    }

    /**
     * 更新时间
     */
    private void updataTimeUi() {
        assert getArguments() != null;
        String time = startTime + "—" + endTime;
        if (week.size() > weekInt) {
            String t = week.get(weekInt).getWeek() + week.get(weekInt).getTime_slot().get(dayInt);
            time = time + "\t\t\t\t" + t;
        }
        content.setText(time);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.dele, R.id.ok, R.id.rel, R.id.content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dele:
                dismissAllowingStateLoss();
                break;
            case R.id.ok:
                Intent in = new Intent(getActivity(), BuyClassDingDanActivity.class);
                in.putExtra("key_id", keyId);
                in.putExtra("type", type);
                in.putExtra("startTime", startTime);
                in.putExtra("endTime", endTime);

                if (week.size() > weekInt) {
                    in.putExtra("day", getWeekDay(week.get(weekInt).getWeek()));
                    in.putExtra("week", week.get(weekInt).getWeek());
                } else {
                    in.putExtra("week", "");
                }
                if (dayStrs.size() > dayInt) in.putExtra("time_slot", dayStrs.get(dayInt));
                else in.putExtra("time_slot", "");
                startActivity(in);
                dismissAllowingStateLoss();
                break;
            case R.id.content:
                Intent intent = new Intent(mContext, KeChengJiHuaListActivity.class);
                intent.putExtra("line_id", keyId);
                intent.putExtra("start_time", startTime);
                intent.putExtra("end_time", endTime);
                intent.putExtra("week", week.get(weekInt).getWeek());
                intent.putExtra("time_slot", week.get(weekInt).getTime_slot().get(dayInt));
                startActivity(intent);
                break;
            case R.id.rel:

                break;
        }
    }

    /**
     * 通过周几字符，转换成对应int
     *
     * @param week
     * @return
     */
    private int getWeekDay(String week) {
        switch (week) {
            case "周一":
                return 1;
            case "周二":
                return 2;
            case "周三":
                return 3;
            case "周四":
                return 4;
            case "周五":
                return 5;
            case "周六":
                return 6;
            case "周日":
                return 7;
            default:
                return 1;
        }
    }

    public void setWeek(List<OnLineXiangQingData.DataBean.WeekBeanX.WeekBean> week) {
        this.week = week;
        dayStrs.clear();
        if (week.size() > weekInt) dayStrs.addAll(week.get(weekInt).getTime_slot());
    }
}
