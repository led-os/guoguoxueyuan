package com.test720.grasshoppercollege.module.gongLue.peiXun.zhaoSheng;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.KeChengData;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.ContactDialog;
import com.test720.grasshoppercollege.myViews.PhoneCalDialog;
import com.test720.grasshoppercollege.myViews.ZhaoShengYuYueDialog;
import com.test720.grasshoppercollege.untils.DaoHanguntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ZhaoShengXiangQingActivity extends BaseToolActivity {

    List<String> titles = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.ziXun)
    TextView ziXun;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.shenQing)
    TextView shenQing;
    @BindView(R.id.yuYue)
    TextView yuYue;
    @BindView(R.id.head)
    ImageView head;
    KeChengData data;
    String currID = "";

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zhao_sheng_xiang_qing;
    }

    @Override
    protected void initData() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
//                dealWithViewPager();
            }
        });

        if (getIntent().getStringExtra("name") != null) {
            title.setText(getIntent().getStringExtra("name"));
        }
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        currID = getIntent().getStringExtra("curr_id");
        if (GongLueData.getInstance().getZhaoXQType() == GongLueData.KECHEN) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("curr_id", currID);
            postResponse(HttpUntil.GetIntent().StrategyTraincurrInfo(), httpParams, 5, true);
        } else {
            HttpParams httpParams = new HttpParams();
            if (currID != null) {
                httpParams.put("curr_id", currID);
            }
            httpParams.put("school_id", GongLueData.getInstance().getSchoolId());
            postResponse(HttpUntil.GetIntent().StrategyChildinterestInfo(), httpParams, 5, true);
        }

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, KeChengData.class);
            currID = data.getData().getCurr_id();

            titles.add("课程详情");
            webView.loadUrl(data.getData().getIntroduce());
            /*WebFragment kecheng = new WebFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", data.getData().getIntroduce());
            kecheng.setArguments(bundle);
            list.add(kecheng);*/

          /*  titles.add("课程安排");
            WebFragment two = new WebFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putString("url", data.getData().getArrange());
            two.setArguments(bundle1);
            list.add(two);*/


            textView3.setText(data.getData().getName());
            Glide.with(mcontext).load(data.getData().getPic()).into(ivHeader);
            Glide.with(mcontext).load(data.getData().getPic_icon()).into(head);

        }

    }

   /* private void dealWithViewPager() {
        int toolBarPositionY = toolbar.getHeight();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup.LayoutParams params = viewPager.getLayoutParams();
            params.height = AppUploatUtils.getScreenHeightPx(mcontext) - toolBarPositionY + 1;
            viewPager.setLayoutParams(params);
        }
    }*/


    @OnClick({R.id.back, R.id.right, R.id.ziXun, R.id.phone, R.id.shenQing, R.id.yuYue, R.id.daohang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right:
                break;
            case R.id.ziXun:
                if (data == null) return;
                ContactDialog contactDialog = new ContactDialog();
                contactDialog.setPath(data.getData().getWx_qrcode());
                contactDialog.setTextStr(data.getData().getConsulting_wx());
                contactDialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.phone:
                if (data == null) return;
                if (TextUtils.isEmpty(phone.getText())) return;
                PhoneCalDialog phoneCalDialog = new PhoneCalDialog();
                phoneCalDialog.setPhone(data.getData().getConsulting_telephone());
                phoneCalDialog.setCallListener(new PhoneCalDialog.CallListener() {
                    @Override
                    public void call() {
                        callPhone(data.getData().getConsulting_telephone());
                    }
                });
                phoneCalDialog.show(getSupportFragmentManager(), "phone");
                break;
            case R.id.shenQing:
                ZhaoShengYuYueDialog zhaoShengYuYueDialog = new ZhaoShengYuYueDialog();
                Bundle bundle = new Bundle();
                bundle.putString("curr_id", currID);
                zhaoShengYuYueDialog.setArguments(bundle);
                zhaoShengYuYueDialog.show(getSupportFragmentManager(), "zhao");
                break;
            case R.id.yuYue:
                ZhaoShengYuYueDialog zhaoShengYuYueDialog1 = new ZhaoShengYuYueDialog();
                Bundle bundle1 = new Bundle();
                bundle1.putString("curr_id", currID);
                zhaoShengYuYueDialog1.setArguments(bundle1);
                zhaoShengYuYueDialog1.show(getSupportFragmentManager(), "zhao");
                break;
            case R.id.daohang:
                if (data != null && data.getData().getAddress() != null) {
                    if (!DaoHanguntil.getInstance().invokingGD(mcontext, data.getData().getAddress())) {
                        DaoHanguntil.getInstance().invokingBD(mcontext, data.getData().getAddress());
                    }
                }
                break;
        }
    }

    /**
     * 拨号
     */
    private void callPhone(final String phoneNum) {
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.CALL_PHONE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);//设置活动类型
                intent.setData(Uri.parse("tel:" + phoneNum));//设置数据
                startActivity(intent);//开启意图
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();


    }


}
