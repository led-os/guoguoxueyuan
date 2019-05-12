package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.BitmapScrollPicker;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.myViews.ScrollPickerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;

public class KuiHuaShouYeActivity extends BaseToolActivity {


    @BindView(R.id.bitSc)
    BitmapScrollPicker bitSc;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    @Override
    protected String setTitle() {
        return "成语宝典";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kui_hua_shou_ye;
    }

    @Override
    protected void initData() {
        CopyOnWriteArrayList<Bitmap> bitmaps = new CopyOnWriteArrayList<Bitmap>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.cykunj));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.cykhlb));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.cykhjl));
        bitSc.setData(bitmaps);

        List<Fragment> list = new ArrayList<>();
        list.add(new NianJiFragment());
        list.add(new NeiBieFragment());
        list.add(new ChengYuJieLong());
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setCurrentItem(1);
        bitSc.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

}
