package com.test720.grasshoppercollege.myViews;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/9.
 */

public class GuoGuoFragmentDialog extends DialogFragment {
    List<String> list = new ArrayList<>();

    List<String> list1 = new ArrayList<>();
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.five)
    TextView five;
    @BindView(R.id.six)
    TextView six;
    @BindView(R.id.gridlay)
    TableLayout gridlay;
    @BindView(R.id.queding)
    ImageView queding;
    Unbinder unbinder;
    Type type;
    MyDialogClick myDialogClick;
    String check;
    @BindView(R.id.one_gou)
    ImageView oneGou;
    @BindView(R.id.two_gou)
    ImageView twoGou;
    @BindView(R.id.three_gou)
    ImageView threeGou;
    @BindView(R.id.four_gou)
    ImageView fourGou;
    @BindView(R.id.five_gou)
    ImageView fiveGou;
    @BindView(R.id.six_gou)
    ImageView sixGou;
    private int postion = 0;

    public void setMyDialogClick(MyDialogClick myDialogClick) {
        this.myDialogClick = myDialogClick;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public interface MyDialogClick {
        void check(String s, int postion);

        void close();
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initEvent();
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    private void initEvent() {
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = one.getText().toString();
                GoneView();
                postion = 0;
                oneGou.setVisibility(View.VISIBLE);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = two.getText().toString();
                GoneView();
                postion = 1;
                twoGou.setVisibility(View.VISIBLE);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = three.getText().toString();
                GoneView();
                postion = 2;
                threeGou.setVisibility(View.VISIBLE);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    dismissAllowingStateLoss();
                    return;
                }
                check = four.getText().toString();
                GoneView();
                postion = 3;
                fourGou.setVisibility(View.VISIBLE);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    dismissAllowingStateLoss();
                    return;
                }
                check = five.getText().toString();
                GoneView();
                postion = 4;
                fiveGou.setVisibility(View.VISIBLE);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = six.getText().toString();
                GoneView();
                postion = 5;
                sixGou.setVisibility(View.VISIBLE);
            }
        });
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDialogClick != null) {
                    myDialogClick.check(check, postion);
                    dismissAllowingStateLoss();
                } else {
                    Toast.makeText(getActivity(), "请选择", Toast.LENGTH_SHORT).show();
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDialogClick != null) {
                    myDialogClick.close();
                    dismissAllowingStateLoss();
                }
            }
        });

    }

    //隐藏之前的勾选
    private void GoneView() {
        switch (postion) {
            case 0:
                oneGou.setVisibility(View.INVISIBLE);
                break;
            case 1:
                twoGou.setVisibility(View.INVISIBLE);
                break;
            case 2:
                threeGou.setVisibility(View.INVISIBLE);
                break;
            case 3:
                fourGou.setVisibility(View.INVISIBLE);
                break;
            case 4:
                fiveGou.setVisibility(View.INVISIBLE);
                break;
            case 5:
                sixGou.setVisibility(View.INVISIBLE);
                break;

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("英语");
        list.add("语文");
        list.add("数学");
        list.add("思维训练");
        list.add("注意力训练");
        list.add("作业");

        list1.add("一年级");
        list1.add("二年级");
        list1.add("三年级");
        list1.add("四年级");
        list1.add("五年级");
        list1.add("六年级");

    }


    @Override
    public void onStart() {
        super.onStart();
        if (type != null) {
            if (type.equals(Type.CLASS)) {
                one.setText(list1.get(0));
                two.setText(list1.get(1));
                three.setText(list1.get(2));
                four.setText(list1.get(3));
                five.setText(list1.get(4));
                six.setText(list1.get(5));
                check = MyApplication.getmInstance().nianji;
                gridlay.setBackgroundResource(R.mipmap.di);
                if (MyApplication.getmInstance().nianji != null) {
                    switch (MyApplication.getmInstance().nianji) {
                        case "一年级":
                            postion = 0;
                            oneGou.setVisibility(View.VISIBLE);
                            break;
                        case "二年级":
                            postion = 1;
                            twoGou.setVisibility(View.VISIBLE);
                            break;
                        case "三年级":
                            postion = 2;
                            threeGou.setVisibility(View.VISIBLE);
                            break;
                        case "四年级":
                            postion = 3;
                            fourGou.setVisibility(View.VISIBLE);
                            break;
                        case "五年级":
                            postion = 4;
                            fiveGou.setVisibility(View.VISIBLE);
                            break;
                        case "六年级":
                            postion = 5;
                            sixGou.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            } else if (type.equals(Type.KEMU)) {
                one.setText(list.get(0));
                two.setText(list.get(1));
                three.setText(list.get(2));
                four.setText(list.get(3));
                five.setText(list.get(4));
                six.setText(list.get(5));
                gridlay.setBackgroundResource(R.mipmap.heiban);
                check = MyApplication.getmInstance().kemu;
                if (MyApplication.getmInstance().kemu != null) {
                    switch (MyApplication.getmInstance().kemu) {
                        case "英语":
                            postion = 0;
                            oneGou.setVisibility(View.VISIBLE);
                            break;
                        case "语文":
                            postion = 1;
                            twoGou.setVisibility(View.VISIBLE);
                            break;
                        case "数学":
                            postion = 2;
                            threeGou.setVisibility(View.VISIBLE);
                            break;
                        case "思维训练":
                            postion = 3;
                            fourGou.setVisibility(View.VISIBLE);
                            break;
                        case "注意力训练":
                            postion = 4;
                            fiveGou.setVisibility(View.VISIBLE);
                            break;
                        case "作业":
                            postion = 5;
                            sixGou.setVisibility(View.VISIBLE);
                            break;
                    }
                }


            }
        }

    }

    /****** 设置外部全透明*/
/* @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);

    }*/

    public enum Type {
        CLASS, KEMU
    }

}
