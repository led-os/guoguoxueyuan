package com.test720.grasshoppercollege.module.userData;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.TuijianData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.ErWei.QRCodeUtil;

public class TuiJianActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.activity_tui_jian_ma)
    RelativeLayout activityTuiJianMa;

    String filePath;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_tui_jian;
    }

    @Override
    protected void initData() {
        filePath = getFilesDir() + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg";
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(mcontext).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().getQrCode(), builder, 1, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    TuijianData data = new Gson().fromJson(str, TuijianData.class);
//                    creatErImg(data.getData().getQrcode_str());
                    if (!data.getData().getQrcode_background().equals("")) {
                        GlideUntil.getInstance().imgUrl(mcontext, imgBack, data.getData().getQrcode_background());
                    }
                    Glide.with(this).load(data.getData().getQrcode()).into(img);
                }
                break;
        }

    }

    /**
     * 生成二维码图片
     */
    private void creatErImg(final String str) {
        //二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = QRCodeUtil.createQRImage(str, 800, 800,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.guoguoqipao),
                        filePath);
                if (success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(BitmapFactory.decodeFile(filePath));
                        }
                    });
                }
            }
        }).start();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
