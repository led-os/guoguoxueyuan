package com.test720.grasshoppercollege.module.youEr.baoBao;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.LianXiData;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.LianXiFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.FixedSpeedScroller;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.ZoomInTransform;
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

public class BaoBaolianXiActivity extends BaseToolActivity {
    List<LianXiFragment> list = new ArrayList<>();
    private FixedSpeedScroller mScroller;
    LianXiData data;
    private DownDialog downDialog;
    BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();


    @Override
    protected String setTitle() {
        return null;
    }

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
    protected int setlayoutResID() {
        return R.layout.activity_bao_baolian_xi;
    }

    @Override
    protected void initData() {
        setBabyId(getIntent().getStringExtra("baby_id"));
        setType(getIntent().getIntExtra("type", 1));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("baby_id", getBabyId());
        Post(HttpUntil.GetIntent().ChildBabyselectThinking(), builder, 1);
        viewPager.setPageTransformer(true, new ZoomInTransform());
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                list.get(position).startMove();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, LianXiData.class);
            String file = HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getPath();
            File f = new File(file);
            String bver = bookDataInsterUntil.getBBVersion(mcontext, data.getData().getZip_name());
            String nver = data.getData().getVersion_number();
            if (bver != null && f.exists() && bver.equals(nver)) {//本地有资源，并且版本已经是最新
                haveZip(data);
                return;
            }
            //初始化下载框
            downDialog = new DownDialog();
            downDialog.setMoudle("点读乐园");
            downDialog.setZipName(data.getData().getZip_name());
            downDialog.setFileName(data.getData().getName());
            downDialog.setPath(data.getData().getZip_path());
            downDialog.setZipOkEnvent(new ZipUntil.ZipOkEnvent() {
                @Override
                public void zipOkEnvent() {
                    bookDataInsterUntil.saveBaoBaoV(mcontext, data.getData().getZip_name(), data.getData().getVersion_number());
                    Message message = new Message();
                    message.what = 1000;
                    mainHandler.sendMessage(message);
                    downDialog.dismissAllowingStateLoss();
                }
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

    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (msg.what == 1000) {//解壓完成
            LogUtil.logError("解压完成，加载界面:" + data.getData().getInfo().size());
            haveZip(data);
        }
    }

    /**
     * 已經獲得了資源包
     *
     * @param data
     */
    private void haveZip(LianXiData data) {
        for (int i = 0; i < data.getData().getInfo().size(); i++) {
            LianXiFragment dianDuFragment = new LianXiFragment();
            dianDuFragment.setDataBean(data.getData().getInfo().get(i));
            dianDuFragment.setViewPager(viewPager);
            list.add(dianDuFragment);
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        if (list.size() > 0) list.get(0).startMove();
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    String babyId;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }
}
