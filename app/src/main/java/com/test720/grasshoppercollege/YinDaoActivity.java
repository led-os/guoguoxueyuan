package com.test720.grasshoppercollege;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import www.test720.mylibrary.SPUtils;

import butterknife.BindView;

public class YinDaoActivity extends BaseToolActivity {

    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yin_dao;
    }

    @Override
    protected void initData() {
        viewpager.setAdapter(new MyAdapter());
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(YinDaoActivity.this).inflate(R.layout.first_view, null);
            ImageView img = view.findViewById(R.id.img);
            Button btn = view.findViewById(R.id.go);
            btn.setVisibility(View.GONE);
            switch (position) {
                case 0:
//                    Glide.with(YinDaoActivity.this).load(UrlUitl.imageUrl + data.getData().getList().get_$1()).into(img);
                    img.setImageResource(R.mipmap.yin_dao_one);
                    break;
                case 1:
//                    Glide.with(YinDaoActivity.this).load(UrlUitl.imageUrl + data.getData().getList().get_$2()).into(img);
                    img.setImageResource(R.mipmap.yin_dao_two);
                    break;
                case 2:
//                    Glide.with(YinDaoActivity.this).load(UrlUitl.imageUrl + data.getData().getList().get_$3()).into(img);
                    img.setImageResource(R.mipmap.yin_dao_three);
                    btn.setVisibility(View.VISIBLE);
                    break;
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SPUtils.putBoolean(YinDaoActivity.this, "first", false);
                    setResult(1, new Intent());
                    finish();
                }
            });
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
