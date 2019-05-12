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
 * Created by 水东流 on 2018/7/16.
 */

public class NianJiDialog extends DialogFragment {
    List<String> list1 = new ArrayList<>();
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.you)
    TextView youEr;
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
    NianJiDialogClick myDialogClick;
    String check;
    @BindView(R.id.you_er_gou)
    ImageView youErGou;
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

    public void setMyDialogClick(NianJiDialogClick myDialogClick) {
        this.myDialogClick = myDialogClick;
    }


    public interface NianJiDialogClick {
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
        View view = inflater.inflate(R.layout.nian_ji_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        initEvent();
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    private void initEvent() {
        youEr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = youEr.getText().toString();
                GoneView();
                postion = -1;
                youErGou.setVisibility(View.VISIBLE);
            }
        });

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
                check = four.getText().toString();
                GoneView();
                postion = 3;
                fourGou.setVisibility(View.VISIBLE);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            case -1:
                youErGou.setVisibility(View.INVISIBLE);
                break;
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
        list1.add("幼儿园");
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

        check = MyApplication.getmInstance().nianji;
        if (MyApplication.getmInstance().nianji != null) {
            switch (MyApplication.getmInstance().nianji) {
                case "幼儿园":
                    postion = -1;
                    youErGou.setVisibility(View.VISIBLE);
                    break;
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

}
