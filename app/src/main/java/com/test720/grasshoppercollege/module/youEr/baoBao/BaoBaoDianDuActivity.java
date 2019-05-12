package com.test720.grasshoppercollege.module.youEr.baoBao;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.DianDuFragment;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.YueDuData;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.FixedSpeedScroller;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.SimplePageTransform;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.downUntil.DownDialog;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaoBaoDianDuActivity extends BaseToolActivity {

    List<Fragment> list = new ArrayList<>();
    private FixedSpeedScroller mScroller;
    YueDuData data;
    BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
    DownDialog downDialog;//下载框

    @Override
    public void setSys() {
        super.setSys();
        noSuPing = true;
        isFull = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bao_bao_dian_du;
    }

    @Override
    protected void initData() {
        setBabyId(getIntent().getStringExtra("baby_id"));
        setType(getIntent().getIntExtra("type", 1));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("baby_id", getBabyId());
        Post(HttpUntil.GetIntent().ChildBabyread(), builder, 1);
        viewPager.setPageTransformer(true, new SimplePageTransform());
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroller(viewPager.getContext(), new AccelerateInterpolator());
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mScroller.setmDuration(1000);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, YueDuData.class);
            String file = HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getPath();
            File f = new File(file);
            String bver = bookDataInsterUntil.getBBVersion(mcontext, data.getData().getZip_name());
            String nver = data.getData().getVersion_number();
            if (f.exists() && bver.equals(nver)) {//本地有资源，并且版本已经是最新
                haveZip(data);
                return;
            }
            //初始化下载框
            downDialog = new DownDialog();
            downDialog.setFileName(data.getData().getName());
            downDialog.setZipName(data.getData().getZip_name());
            downDialog.setMoudle("点读乐园");
            downDialog.setPath(data.getData().getZip_path());
            downDialog.setZipOkEnvent(() -> {
                bookDataInsterUntil.saveBaoBaoV(mcontext, data.getData().getZip_name(), data.getData().getVersion_number());
                Message message = new Message();
                message.what = 1000;
                mainHandler.sendMessage(message);
                downDialog.dismissAllowingStateLoss();
            });
            if (f.exists()) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ZipUntil zipUntil = new ZipUntil();
                        zipUntil.deleZipFile(data.getData().getPath(), mcontext);
                        downDialog.show(getSupportFragmentManager(), "down");
                    }
                };
                mainHandler.postDelayed(runnable, 0);
            } else {
                downDialog.show(getSupportFragmentManager(), "down");
            }
        }
    }

    /**
     * 獲取到本地資源后的艦艇
     *
     * @param data
     */
    private void haveZip(YueDuData data) {
        for (int i = 0; i < data.getData().getInfo().size(); i++) {
            DianDuFragment dianDuFragment = new DianDuFragment();
            dianDuFragment.setDataBean(data.getData().getInfo().get(i));
            dianDuFragment.setViewPager(viewPager);
            dianDuFragment.setIndex(i);
            list.add(dianDuFragment);
        }
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (msg.what == 1000) {//解壓完成
            haveZip(data);
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    String babyId;

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }
}
