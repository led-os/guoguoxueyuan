package com.test720.grasshoppercollege.myViews.choosetime;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 *
 */
public class DataPickerPopWindow extends PopupWindow implements OnWheelChangedListener{

    private final int y;
    private final int m;
    private final int d;
    private Context context;
    private LayoutInflater layoutInflater;
    private WheelView yearView;
    private WheelView monthView;
    private WheelView dayView;
    private List<String> dayList = new ArrayList<String>();
    private int thisyear;
    private int thismonth;
    private int thisday;
    public DataPickerPopWindow(Context context,int thisyear,int thismonth,int thisday) {
        // TODO Auto-generated constructor stub
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.data_picker, null);
        this.context = context;
        this.thisyear=thisyear;
        this.thismonth=thismonth;
        this.thisday=thisday;
        this.setContentView(v);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setAnimationStyle(android.R.style.Animation_InputMethod);
        this.setFocusable(true);
//		this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //获得当前时间的月份，月份从0开始所以结果要加1
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH )+1;
        d = cal.get(Calendar.DAY_OF_MONTH );

        initData();
        initPop(v);
    }
    //初始化Pop
    private void initPop(View viewGroup){
        yearView = (WheelView) viewGroup.findViewById(R.id.year);
        monthView = (WheelView) viewGroup.findViewById(R.id.month);
        dayView = (WheelView) viewGroup.findViewById(R.id.day);
//        yearView.setListener(this);
//        monthView.setListener(this);
//        dayView.setListener(this);


        //确定
        viewGroup.findViewById(R.id.data_picker_complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthday=(yearView.getCurrentItem()+1900)+"-"+(monthView.getCurrentItem()+1)+"-"+(dayView.getCurrentItem()+1);
                pdpw.SaveData(birthday);
                dismiss();
            }
        });

        //取消
        viewGroup.findViewById(R.id.data_picker_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        System.out.println((thisyear-1900)+"----"+thismonth+"----"+thisday);
        yearView.setViewAdapter(new NumericWheelAdapter(context, 1900, y, "年"));
        yearView.setCurrentItem(thisyear-1900);

        monthView.setViewAdapter(new NumericWheelAdapter(context,1, 12,"月"));
        monthView.setCurrentItem(thismonth);
        int day=getDay(thisyear, thismonth+1);
        dayView.setViewAdapter(new NumericWheelAdapter(context, 1, day,"日"));
        dayView.setCurrentItem(thisday-1);
        yearView.setCyclic(false);// 可循环滚动
        monthView.setCyclic(false);// 可循环滚动
        dayView.setCyclic(false);// 可循环滚动
        yearView.addChangingListener(this);
        monthView.addChangingListener(this);
        dayView.addChangingListener(this);
//        monthView.setItems(monthList);
//        monthView.setInitPosition(thismonth);
//        dayView.setItems(dayList);
//        dayView.setInitPosition(thisday);
    }

    /**
     * 初始化时间
     *
     */
    private void initData() {
        for (int i = 1; i < 32; i++) {
            dayList.add(i + "日");
        }
    }

    /**
     * @param year
     * @param month
     * @return
     */

    private int getDay(int year, int month) {
        int size = dayList.size();
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                for (int i = size; i < 31; i++) {
                    dayList.add(i + 1 + "日");
                }
                break;
            case 2:
                day = flag ? 29 : 28;
                for (int i = day; i < size; i++) {
                    dayList.remove(dayList.size() - 1);
                }
                if (size == 28 && flag) {
                    dayList.add(29 + "日");
                }

                break;
            default:
                day = 30;
                for (int i = size; i < 30; i++) {
                    dayList.add(i + 1 + "日");
                }
                if (size == 31) {
                    dayList.remove(size - 1);
                }
                break;
        }
        return day;
    }
    /* (non-Javadoc)
     * @see com.wheelviewcity.OnWheelChangedListener#onChanged(com.wheelviewcity.WheelView, int, int)
     */
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel!=dayView) {
            int year=yearView.getCurrentItem();
            int month=monthView.getCurrentItem();
            int day=getDay(year+1991, month+1);
            dayView.setViewAdapter(new NumericWheelAdapter(context, 1, day,"日"));
        }
    }


    private PopDataPickerWindow pdpw;

    public void setOnBirthdayListener(PopDataPickerWindow pdpw) {
        this.pdpw = pdpw;
    }

    public interface PopDataPickerWindow {
        void SaveData(String birthday);
    }

}
