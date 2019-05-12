package com.test720.grasshoppercollege.module.userData.geRenZiLiao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.myViews.PopWindow;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class HeaderActivity extends BaseToolActivity implements HeaderAdapter.ImgClick {

    @BindView(R.id.header)
    RoundedImageView header;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.lv)
    TextView lv;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.headerImg)
    ImageView headerImg;
    @BindView(R.id.headerTxt)
    TextView headerTxt;
    @BindView(R.id.headLin)
    LinearLayout headLin;
    @BindView(R.id.toushiImg)
    ImageView toushiImg;
    @BindView(R.id.toushiTxt)
    TextView toushiTxt;
    @BindView(R.id.zhuangshiLin)
    LinearLayout zhuangshiLin;
    @BindView(R.id.root)
    LinearLayout root;
    private List<String> mTitle = new ArrayList<String>();
    String headerUrl;//点击已经获得的头像，而获得的头像地址
    HeaderFragment headerFragment;
    TouShiFragment touShiFragment;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_header;
    }

    @Override
    protected void initData() {
        name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        Glide.with(this).load(MyApplication.getmInstance().userData.getData().getHeader()).error(R.mipmap.pic_head_default).into(header);
        lv.setText("lv:" + MyApplication.getmInstance().userData.getData().getLevel());
        headerFragment = new HeaderFragment();
        touShiFragment = new TouShiFragment();
        headerFragment.setImgclick(this);
        touShiFragment.setImgclick(this);
        list.add(headerFragment);
        list.add(touShiFragment);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setScanScroll(false);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (!codeIsOne(str)) {//设置失败，则加载本来的头像
                    Glide.with(this).load(MyApplication.getmInstance().userData.getData().getHeader()).error(R.mipmap.pic_head_default).into(header);
                } else {
                    MyApplication.getmInstance().userData.getData().setHeader(headerUrl);//设置成功则替换全局头像地址

                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    //更新列表布局
                    if (viewPager.getCurrentItem() == 1) {
                        touShiFragment.upData();
                    } else {
                        headerFragment.upData();
                    }
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.headLin, R.id.zhuangshiLin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headLin:
                headerImg.setImageResource(R.mipmap.touxiang1);
                headerTxt.setTextColor(getResources().getColor(R.color.appColor));
                toushiImg.setImageResource(R.mipmap.zhuangshi1);
                toushiTxt.setTextColor(getResources().getColor(R.color.light_gray));
                viewPager.setCurrentItem(0);
                break;
            case R.id.zhuangshiLin:
                headerImg.setImageResource(R.mipmap.touxiang2);
                headerTxt.setTextColor(getResources().getColor(R.color.light_gray));
                toushiImg.setImageResource(R.mipmap.zhuangshi2);
                toushiTxt.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    /*已经获得的头像点击事件*/
    @Override
    public void ObtainClick(final String id, final String url) {
        View view = LayoutInflater.from(this).inflate(R.layout.header_pop, null);
        final PopWindow popWindow = new PopWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        TextView textView = view.findViewById(R.id.ok);
        textView.setText("设置为头像");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mcontext).load(url).error(R.mipmap.pic_head_default).into(header);
                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                formBuilder.add("header_id", id);//请求参数二
                Post(HttpUntil.GetIntent().setMyHeader(), formBuilder, 1, false);
                headerUrl = url;
                popWindow.dismiss();
            }
        });
        popWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);

    }

    /*未获得的头像点击*/
    @Override
    public void NoObtainClick(final String id, final String points) {
        View view = LayoutInflater.from(this).inflate(R.layout.header_pop, null);
        final PopWindow popWindow = new PopWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.setOutsideTouchable(true);
        TextView textView = view.findViewById(R.id.ok);
        textView.setText("兑换头像");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int my = Integer.parseInt(MyApplication.getmInstance().userData.getData().getPoints());
                int need = Integer.parseInt(points);
                if (my < need) {
                    ShowToast("果果豆不足，无法兑换");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setMessage("此头像需要" + points + "果果豆，是否兑换")
                        .setTitle("提示")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("header_id", id);//请求参数二
                                Post(HttpUntil.GetIntent().exchangeHeader(), formBuilder, 2, false);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create().show();
            }
        });
        popWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);


    }

}
