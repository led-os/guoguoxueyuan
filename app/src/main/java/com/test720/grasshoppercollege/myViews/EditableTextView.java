package com.test720.grasshoppercollege.myViews;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;

import java.util.ArrayList;

/**
 * Created by zd on 2018/3/1.
 */

public class EditableTextView extends android.support.v7.widget.AppCompatTextView {

    /**
     * 存放Span影响内容的边界信息对象列表
     */
    private ArrayList<RangBean> ranges;
    /**
     * 可变长度的可分组字符串
     */
    private SpannableStringBuilder ssb;
    /**
     * 存放答案的列表
     */
    private ArrayList<String> answers;
    /**
     * 用来输入答案的可编辑文本框控件
     */
    private EditText input;
    /**
     * 用来确定答案的按钮控件
     */
    private Button sure;
    /**
     * 用来展示输入答案的可编辑文本框和确定按钮界面的popupwindow
     */
    private PopupWindow popupWindow;
    /**
     * 软键盘的高度，我们可以认为屏幕高度的三分之一就是软键盘打开时的高度
     */
    private int softInputHeight;


    public EditableTextView(Context context) {
        super(context);

        init();
    }

    public EditableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public EditableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * 初始化
     */
    private void init() {

        answers = new ArrayList<>();
        ranges = new ArrayList<>();
        ssb = new SpannableStringBuilder();

        //计算屏幕高度的三分之一，赋值给软键盘高度
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        softInputHeight = dm.heightPixels / 3;

        //加载一个输入框和确定按钮的布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.input, null);
        input = (EditText) view.findViewById(R.id.editable_textview_input);
        sure = (Button) view.findViewById(R.id.editable_textview_sure);

        //初始化popupwindow
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, dp2px(40));
        popupWindow.setFocusable(true);
        //设置popupwindow会被软键盘顶上去，而不是覆盖掉
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //下面的两个方法一起使用，设置点击popupwindow边界外部时使得popupwindow消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);

        initSoftListener();
    }

    /**
     * 设置显示内容和内容中受span影响的边界信息列表,
     * 使得文本框中的显示内容的下划线部分的字体颜色改变，并且可以点击，
     * 点击后弹出输入框，输入内容确定后替换下划线部分的内容
     *
     * @param str       显示的内容
     * @param rangBeans 内容中受span影响的边界信息列表
     *                  例如：
     *                  str = "本人持有____国学生签证，在____国院校就读；"
     *                  RangBean ranges = new ArrayList<>();
     *                  ranges.add(new RangBean(4, 8));
     *                  ranges.add(new RangBean(15, 19));
     *                  setData(str, ranges);
     */
    public void setData(String str, ArrayList<RangBean> rangBeans) {

        if (TextUtils.isEmpty(str) || rangBeans == null) {
//            Toast.makeText(getContext(), "参数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        str = str.replace("\\n", "\n");
        ssb.clear();
        ssb.append(str);
        ranges.clear();
        ranges.addAll(rangBeans);

        for (int i = 0; i < ranges.size(); i++) {
            RangBean bean = ranges.get(i);

            //设置文字颜色
            //这里注意一下setSpan的flag有4中取值（start的取值范围从0开始）：
            //SPAN_INCLUSIVE_EXCLUSIVE表示插入start前的内容和[start,end)左闭右开区间内的内容受到span的影响；
            //SPAN_INCLUSIVE_INCLUSIVE表示插入start前的内容，插入end后的内容和[start,end)左闭右开区间内的内容受到span的影响；
            //SPAN_EXCLUSIVE_EXCLUSIVE表示只有[start,end)左闭右开区间内的内容受到span的影响；
            //SPAN_EXCLUSIVE_INCLUSIVE表示插入end后的内容和[start,end)左闭右开区间内的内容受到span的影响。
            //值得一说的是网上查到的大部分资料都是像上面这么介绍这四个flag的影响的，
            //但是这四个flag的影响起作用只在可编辑控件中起作用
            //例如editext中可以，如果想体验可以把我MainActivty注释的edittext
            // 和activity_main里的相关代码解除注释试一试，在不可编辑控件中这四个flag不起任何
            //作用，例如textview，因为不可编辑控件根本没办法在span生效的内容前或者后插入新的内容，
            // 所以在不可编辑的控件中使用4个flag中的任意一个都可以，你可以试着把下面的flag修改试试效果

            /*设置颜色*/
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.appColor));
            ssb.setSpan(colorSpan, bean.getStart(), bean.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置可点击
            ClickableSpan clickableSpan = new MyClickableSpan(i);
            ssb.setSpan(clickableSpan, bean.getStart(), bean.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            /*设置字体大小*/
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(80);
            ssb.setSpan(absoluteSizeSpan, bean.getStart(), bean.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            /*背景色*/
            answers.add("");
        }

        //注意这里必须为文本框设置这个属性，clickablespan才生效
        setMovementMethod(LinkMovementMethod.getInstance());
        //将可变长度可分组字符串设置到文本框里
        setText(ssb);
    }

    private class MyClickableSpan extends ClickableSpan {

        private int index;

        /**
         * 构造方法
         *
         * @param index 点击位置在边界列表中对应的index
         */
        public MyClickableSpan(int index) {

            this.index = index;
        }

        //去除连接下划线
        @Override
        public void updateDrawState(TextPaint ds) {
            /**set textColor**/
//            ds.setColor(ds.linkColor);
            ds.setColor(getResources().getColor(R.color.appColor));
            /**Remove the underline**/
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {

            //将答案展示在输入框中
            input.setText(answers.get(index));
            //设置光标移动到答案最后
            input.setSelection(input.length());
            //显示popupwindow
            popupWindow.showAtLocation(EditableTextView.this, Gravity.BOTTOM, 0, 0);
            //下面两行是弹出软键盘
            InputMethodManager innputMethodManager = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            innputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            sure.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = input.getText().toString();
                    //如果输入框中的答案为空，则用空格代替
                    if (TextUtils.isEmpty(answer)) {
                        //隐藏popupwindow
                        popupWindow.dismiss();
                        //隐藏输入框
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        return;
                    }
                    //将输入框中的答案根据位置添加到答案列表中
                    answers.set(index, answer);
                    //获取当前位置的边界信息
                    RangBean bean = ranges.get(index);
                    //将可变长度可分组字符串中边界信息对应位置替换成答案
                    ssb.replace(bean.getStart(), bean.getEnd(), answer);
                    //计算边界信息中的答案长度与输入的答案长度的差值
                    int change = bean.getEnd() - bean.getStart() - answer.length();
                    //更新当前位置及之后的边界信息
                    for (int i = index; i < ranges.size(); i++) {
                        RangBean rb = ranges.get(i);
                        //当前位置的起始位置信息不变，之后的所有位置的起始边界信息根据差值移动
                        if (i != index) {
                            rb.setStart(rb.getStart() - change);
                        }
                        //当前位置以及之后所有位置的结束边界信息根据差值移动
                        rb.setEnd(rb.getEnd() - change);
                    }
                    //设置下划线
                    UnderlineSpan underlineSpan = new UnderlineSpan();
                    ssb.setSpan(underlineSpan, bean.getStart(), bean.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //将文本框中的内容更新
                    setText(ssb);
                    //隐藏popupwindow
                    popupWindow.dismiss();
                    //隐藏输入框
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                }
            });
        }
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * 设置软键盘的关闭和打开监听，要使用这个方法必须手机api在11或以上，
     * 并且activity在清单配置文件中设置android:windowSoftInputMode="adjustResize"
     */
    private void initSoftListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                ((Activity) getContext()).findViewById(android.R.id.content)
                        .addOnLayoutChangeListener(new OnLayoutChangeListener() {
                            @Override
                            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                                //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
                                if (oldBottom != 0 && bottom != 0 && ((oldBottom - bottom) > softInputHeight)) {
                                    onOpenSoftInput();

                                } else if (oldBottom != 0 && bottom != 0 && ((bottom - oldBottom) > softInputHeight)) {
                                    onCloseSoftInput();
                                }
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 当软键盘打开时回调
     */
    private void onOpenSoftInput() {
        Log.i("zhangdi", "软件盘打开");
    }

    /**
     * 当软键盘关闭时回调
     */
    private void onCloseSoftInput() {
        Log.i("zhangdi", "软件盘关闭");
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public EditText getInput() {
        return input;
    }
}
