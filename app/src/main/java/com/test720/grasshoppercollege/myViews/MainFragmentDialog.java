package com.test720.grasshoppercollege.myViews;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.fragment.MainPoPFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/6/6.
 */

public class MainFragmentDialog extends DialogFragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.close)
    ImageView close;
    Unbinder unbinder;
    @BindView(R.id.lin)
    LinearLayout lin;


    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.main_pop, container, false);
        Window window = this.getDialog().getWindow();
        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        //设置dialog的动画
        lp.windowAnimations = R.style.take_photo_anim;
        window.setAttributes(lp);
        unbinder = ButterKnife.bind(this, view);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MainPoPFragment mainPoPFragment = new MainPoPFragment();
            mainPoPFragment.setClick(new MainPoPFragment.Click() {
                @Override
                public void click() {
                    dismissAllowingStateLoss();
                }
            });
            list.add(mainPoPFragment);
        }
        AcoesMuscularesAdapter ama = new AcoesMuscularesAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(ama);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.NoTitleFullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
          /*点击目录，跳转*/
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    class AcoesMuscularesAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public AcoesMuscularesAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
