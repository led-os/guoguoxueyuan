package com.test720.grasshoppercollege;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test720.grasshoppercollege.fragment.BeiBaoFragment;
import com.test720.grasshoppercollege.fragment.ChengBaoFragment;
import com.test720.grasshoppercollege.fragment.TanXianListFragment;
import com.test720.grasshoppercollege.fragment.YouErTanXianFragment;
import com.test720.grasshoppercollege.module.gongLue.GongLueFragment;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;
import com.test720.grasshoppercollege.myViews.MainViewPager;
import com.test720.grasshoppercollege.untils.LoginUntil;
import com.test720.grasshoppercollege.untils.ShenHeUntil;
import com.test720.grasshoppercollege.untils.SnackbarUtils;
import com.test720.grasshoppercollege.untils.dingWei.LocationService;
import com.test720.grasshoppercollege.untils.musicMedia.MusicService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class MainActivity extends BaseToolActivity {

    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.lin4)
    LinearLayout lin4;
    @BindView(R.id.img5)
    ImageView img5;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.lin5)
    LinearLayout lin5;
    @BindView(R.id.main_tabhost)
    LinearLayout mainTabhost;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.fragme)
    MainViewPager fragme;

    List<Fragment> list = new ArrayList<>();
    // 管理Fragment
    private ChengBaoFragment chengBaoFragment = new ChengBaoFragment();
    private BeiBaoFragment beiBaoFragment = new BeiBaoFragment();
    private TanXianListFragment tanXianFragment = new TanXianListFragment();
    private YouErTanXianFragment youErTanXianFragment = new YouErTanXianFragment();
    private GongLueFragment fragmentGongLv = new GongLueFragment();
    int index = 0;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //城堡
        if (intent.getStringExtra("chengbao") != null) {
            chengBao();
        }
        //攻略
        if (intent.getStringExtra("gonglue") != null) {
            gongLue();
        }

        //探险
        if (intent.getStringExtra("tanxian") != null) {
            tanXian();

        }

    }

    private void tanXian() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) {
            return;
        }
        playAnimBack();
        index = 1;
        if (MyApplication.getmInstance().nianji.equals("幼儿园")) {
            fragme.setCurrentItem(2);
        } else {
            fragme.setCurrentItem(1);
        }
        playAnim();
    }

    private void gongLue() {
        playAnimBack();
        index = 3;
        fragme.setCurrentItem(3);
        playAnim();
    }

    private void chengBao() {
        playAnimBack();
        index = 0;
        fragme.setCurrentItem(0);
        playAnim();
    }

    private void beiBao() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) {
            return;
        }
        playAnimBack();
        index = 4;
        fragme.setCurrentItem(4);
        playAnim();
    }


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //審核模式判斷
        if (ShenHeUntil.getInstance().isShenHe()) {
            lin2.setVisibility(View.INVISIBLE);
            lin4.setVisibility(View.INVISIBLE);
            lin3.setEnabled(false);
        }

        fragme.setOffscreenPageLimit(0);
        list.clear();
        list.add(chengBaoFragment);
        list.add(tanXianFragment);
        list.add(youErTanXianFragment);
        list.add(fragmentGongLv);
        list.add(beiBaoFragment);
        fragme.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        // 登陆融云
        MyApplication.getmInstance().RongListener();
    }

    private void run() {
        //获取定信息
        Intent intent = new Intent(mcontext, LocationService.class);
        startService(intent);
    }

    /**
     * 权限获取回调
     *
     * @param requestCode  定位权限
     * @param permissions  权限
     * @param grantResults 权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //回调，判断用户到底点击是还是否。
        //如果同时申请多个权限，可以for循环遍历
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            run();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(mcontext, "需要获得定位权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @OnClick({R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4, R.id.lin5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin1:
                chengBao();
                break;
            case R.id.lin2:
                tanXian();
                break;
            case R.id.lin3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                centerPop();
                break;
            case R.id.lin4:
                gongLue();
                break;
            case R.id.lin5:
                beiBao();
                break;
        }
    }


    /*点击移动,上移动画*/
    private void playAnim() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            case 3:
                view = lin4;
                break;
            case 4:
                view = lin5;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -100, 0, -50);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*点击移动,复原动画*/
    private void playAnimBack() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            case 3:
                view = lin4;
                break;
            case 4:
                view = lin5;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }


    //中间位置弹出框
    public void centerPop() {
        jumpToActivity(ShangChengActivity.class, false);
    }

    /**
     * 返回退出
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//&& event.getAction() == KeyEvent.ACTION_DOWN
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                SnackbarUtils.Short(fragme, "再点击一次，退出蝈蝈学苑！")
                        .gravityFrameLayout(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM)
                        .info()
                        .messageRight()
                        .leftAndRightDrawable(R.mipmap.labasy, null)
                        .messageColor(getResources().getColor(R.color.white))
                        .show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(mcontext,
                        MusicService.class);
                stopService(intent);
                //断开融云连接
                RongIM.getInstance().disconnect();
                //结束定位广播
                Intent intent1 = new Intent(mcontext, LocationService.class);
                startService(intent1);
                //清除缓存数据
                LoginUntil.getInstance().cleanCashData();
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());  //获取PID
                System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
