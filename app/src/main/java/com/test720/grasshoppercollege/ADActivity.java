package com.test720.grasshoppercollege;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ADActivity extends BaseToolActivity {


    @BindView(R.id.imageView)
    ImageView imageView;
    String location_type;
    String location_link;
    @BindView(R.id.time)
    TextView time;
    CountDownTimerUtil countDownTimerUtil;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ad;
    }

    @Override
    protected void initData() {
        String pic = getIntent().getStringExtra("pic");
        location_type = getIntent().getStringExtra("location_type");
        location_link = getIntent().getStringExtra("location_link");
        if (pic != null) {
            Glide.with(this)
                    .load(pic)
                    .error(R.drawable.img_err)
                    .into(imageView)
            ;
        }
        countDownTimerUtil = new CountDownTimerUtil(7);
        countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
            @Override
            public void upDataUi(int t) {
                String tu = "跳过" + String.valueOf(t) + "s";
                time.setText(tu);
            }

            @Override
            public void timeIsOver() {
                setResult(1, new Intent());
                finish();
            }
        });
        countDownTimerUtil.start();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            countDownTimerUtil.continueTime();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimerUtil.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (countDownTimerUtil != null) {
            countDownTimerUtil.continueTime();
        }
    }

    @OnClick({R.id.imageView, R.id.time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                if (imageView.getImageAlpha() == R.drawable.img_err) {
                    ShowToast("正在重新加载");
                }
                if (location_link == null || location_link.equals("")) return;
                if (location_type.equals("3")) {
                    try {
//                        Intent intent = new Intent();
//                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(location_link);
                        Intent intent = new Intent(Intent.ACTION_VIEW, content_url);
                        startActivity(intent);

                        startActivityForResult(intent, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (location_type.equals("2")) {
                    Intent in = new Intent(this, WebViewActivity.class);
                    in.putExtra("url", location_link);
                    in.putExtra("title", "蝈蝈学苑");
                    startActivityForResult(in, 1);
                }
                break;
            case R.id.time:
                countDownTimerUtil.over();
                setResult(1, new Intent());
                finish();
                break;
        }
    }
}
