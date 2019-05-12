package com.test720.grasshoppercollege.myViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/7/2.
 * <p>
 * 选择填空按钮
 */

public class ChooseTextView extends android.support.v7.widget.AppCompatTextView {
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
    private ArrayList<String> answers;//我的每个空格的答案
    private ArrayList<String> answersList;//标签上显示的答案集合

    /**
     * 用来输入答案的标签
     */
    TextView one, two, three, four, five, six, seven, eight, nine;
    /**
     * 用来展示输入答案的可编辑文本框和确定按钮界面的popupwindow
     */
    private PopupWindow popupWindow;


    public ChooseTextView(Context context) {
        super(context);
        init();
    }

    public ChooseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChooseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {

        answers = new ArrayList<>();
        ranges = new ArrayList<>();
        ssb = new SpannableStringBuilder();
        answers = new ArrayList<>();

        //加载一个输入框和确定按钮的布局
        @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.choose_bu_chong_pop, null);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);

        //初始化popupwindow
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        //设置popupwindow会被软键盘顶上去，而不是覆盖掉
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //下面的两个方法一起使用，设置点击popupwindow边界外部时使得popupwindow消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);

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
    public void setData(String str, ArrayList<RangBean> rangBeans, List<String> answersList) {

        if (TextUtils.isEmpty(str) || rangBeans == null) {
//            Toast.makeText(getContext(), "参数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        str = str.replace("\\n", "\n");
        ssb.clear();
        ssb.append(str);
        ranges.clear();
        ranges.addAll(rangBeans);
        answers = new ArrayList<>(rangBeans.size());
        this.answersList = new ArrayList<>();
        this.answersList.clear();
        this.answersList.addAll(answersList);
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
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.gwe));
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
        //将可变长度可分组字符串设置到文本框里
        setText(ssb);
        //注意这里必须为文本框设置这个属性，clickablespan才生效
        setMovementMethod(LinkMovementMethod.getInstance());
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
            //将答案展示在标签中
            initFlow(index);
            //显示popupwindow
            popupWindow.showAtLocation(ChooseTextView.this, Gravity.BOTTOM, 0, 0);
        }
    }

    /*将答案展示到弹框*/
    private void initFlow(final int index) {
        for (int i = 0; i < answersList.size(); i++) {
            final int j = i;
            switch (i) {
                case 0:
                    one.setVisibility(VISIBLE);
                    one.setText(answersList.get(i));
                    one.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 1:
                    two.setVisibility(VISIBLE);
                    two.setText(answersList.get(i));
                    two.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 2:
                    three.setVisibility(VISIBLE);
                    three.setText(answersList.get(i));
                    three.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 3:
                    four.setVisibility(VISIBLE);
                    four.setText(answersList.get(i));
                    four.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 4:
                    five.setVisibility(VISIBLE);
                    five.setText(answersList.get(i));
                    five.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 5:
                    six.setVisibility(VISIBLE);
                    six.setText(answersList.get(i));
                    six.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });

                    break;
                case 6:
                    seven.setVisibility(VISIBLE);
                    seven.setText(answersList.get(i));
                    seven.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 7:
                    eight.setVisibility(VISIBLE);
                    eight.setText(answersList.get(i));
                    eight.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
                case 8:
                    nine.setVisibility(VISIBLE);
                    nine.setText(answersList.get(i));
                    nine.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickAnswer(index, j);
                        }
                    });
                    break;
            }
        }
    }

    /**
     * 点击标签的答案，将答案传递到空格
     *
     * @param index 当前点击的空格位置
     * @param j     选择的标签答案位置
     */
    private void clickAnswer(int index, int j) {
        answers.set(index, answersList.get(j));
        //获取当前位置的边界信息
        RangBean bean = ranges.get(index);
        //将可变长度可分组字符串中边界信息对应位置替换成答案
        ssb.replace(bean.getStart(), bean.getEnd(), answersList.get(j));
        //计算边界信息中的答案长度与输入的答案长度的差值
        int change = bean.getEnd() - bean.getStart() - answersList.get(j).length();
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
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

}
