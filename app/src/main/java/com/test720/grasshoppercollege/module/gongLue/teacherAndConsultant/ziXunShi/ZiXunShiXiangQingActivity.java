package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.FuWuDialog;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.FuWuFragment;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.PeiXunTeacherXiangQingData;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ZiXunShiXiangQingActivity extends BaseToolActivity {


    List<String> titles = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();

    PeiXunTeacherXiangQingData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zi_xun_shi_xiang_qing;
    }

    @Override
    protected void initData() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        viewPager.setOffscreenPageLimit(4);
        HttpParams httpParams = new HttpParams();
        httpParams.put("tid", getIntent().getStringExtra("tid"));
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("status", getIntent().getStringExtra("status"));
        postResponse(HttpUntil.GetIntent().StrategyteacherInfo(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    initUi(str);
                }
                break;
            case 6:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        if (jsonObject.getJSONObject("data").getString("guanzhu").equals("0")) {
                            guanZhu.setText("关注");
                            Drawable drawable = getResources().getDrawable(R.mipmap.aixinsc);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            guanZhu.setCompoundDrawables(drawable, null, null, null);
                        } else {
                            guanZhu.setText("取消关注 ");
                            Drawable drawable = getResources().getDrawable(R.mipmap.aixin2);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            guanZhu.setCompoundDrawables(drawable, null, null, null);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

    }

    /**
     * @param str 网络返回
     */
    private void initUi(String str) {
        data = new Gson().fromJson(str, PeiXunTeacherXiangQingData.class);
        if (data.getData().getInfo().getGuanzhu().equals("0")) {
            guanZhu.setText("关注");
            Drawable drawable = getResources().getDrawable(R.mipmap.aixinsc);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            guanZhu.setCompoundDrawables(drawable, null, null, null);
        } else {
            guanZhu.setText("取消关注 ");
            Drawable drawable = getResources().getDrawable(R.mipmap.aixin2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            guanZhu.setCompoundDrawables(drawable, null, null, null);
        }
        GlideUntil.getInstance().headByUrl(mcontext, head, data.getData().getInfo().getHeader());
        name.setText(data.getData().getInfo().getNickname());
        String a = data.getData().getInfo().getProvince() +
                data.getData().getInfo().getCity() +
                data.getData().getInfo().getArea();
        adress.setText(a);
        flow.setAdapter(new TagAdapter<String>(data.getData().getInfo().getTag()) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(o);
                textView.setTextSize(14);
                textView.setBackgroundResource(R.drawable.white_bian_kuang_no_back);
                textView.setTextColor(getResources().getColor(R.color.white));
                return view;
            }
        });

        thanksNum.setText(data.getData().getInfo().getComment_count());
        ziXunNum.setText(data.getData().getInfo().getConsulting_number());
        fenShiNum.setText(data.getData().getInfo().getFans());

        //证书照片
        slider.removeAllSliders();
        for (int i = 0; i < data.getData().getInfo().getImg().size(); i++) {
            TextSliderView textSliderView = new TextSliderView(mcontext);
            textSliderView
                    .description("")//描述
                    .image(data.getData().getInfo().getImg().get(i))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", "");//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间


        titles.add("咨询师服务");
        titles.add("咨询师答疑");


        FuWuFragment fuWuFragment = new FuWuFragment();
        fuWuFragment.setService(data.getData().getService());
        list.add(fuWuFragment);

        XiangQingWenDaFragment wenDaListFragment = new XiangQingWenDaFragment();
        wenDaListFragment.setAnswer(data.getData().getAnswer());
        list.add(wenDaListFragment);

       /* TeacherPinLunFragment teacherPinLunFragment = new TeacherPinLunFragment();
        teacherPinLunFragment.setList(data.getData().getComment());
        list.add(teacherPinLunFragment);*/

        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list, titles));
        tabs.setupWithViewPager(viewPager);
    }

    private void dealWithViewPager() {
        int toolBarPositionY = toolbar.getHeight() + 20;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup.LayoutParams params = viewPager.getLayoutParams();
            params.height = AppUploatUtils.getScreenHeightPx(mcontext) - toolBarPositionY + 1;
            viewPager.setLayoutParams(params);
        }
    }

    @OnClick({R.id.back, R.id.guan_zhu, R.id.yuYue, R.id.onLineAnswer, R.id.seeJieShao, R.id.head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head:
            case R.id.seeJieShao:
                if (data == null) return;
                Intent intent11 = new Intent(mcontext, WebViewActivity.class);
                intent11.putExtra("title", data.getData().getInfo().getNickname());
                intent11.putExtra("path", data.getData().getInfo().getConsultant_desc());
                jumpToActivity(intent11, false);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.guan_zhu:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                HttpParams httpParams = new HttpParams();
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("teacher_id", getIntent().getStringExtra("tid"));
                httpParams.put("status", getIntent().getStringExtra("status"));
                postResponse(HttpUntil.GetIntent().StrategyguanzhuTeacher(), httpParams, 6, true);
                break;
            case R.id.onLineAnswer:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                if (data != null) {
                    Intent intent = new Intent(mcontext, AnswerlistActivity.class);
                    intent.putExtra("tid", getIntent().getStringExtra("tid"));
                    jumpToActivity(intent, false);
                }
                break;
            case R.id.yuYue:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                if (data != null) {
                    FuWuDialog buyDialog = new FuWuDialog();
                    buyDialog.setService(data.getData().getService());
                    buyDialog.setGetId(new FuWuDialog.GetId() {
                        @Override
                        public void getId(final String Id) {
                            Intent intent = new Intent(mcontext, YuYueDingDanActivity.class);
                            intent.putExtra("service_id", Id);
                            intent.putExtra("tid", getIntent().getStringExtra("tid"));
                            jumpToActivity(intent, false);
                        }
                    });
                    buyDialog.show(getSupportFragmentManager(), "buy");
                }

                break;
        }
    }


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.head)
    RoundedImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.flow)
    TagFlowLayout flow;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.thanksNum)
    TextView thanksNum;
    @BindView(R.id.zi_xun_num)
    TextView ziXunNum;
    @BindView(R.id.fen_shi_num)
    TextView fenShiNum;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.guan_zhu)
    TextView guanZhu;
    @BindView(R.id.yuYue)
    TextView yuYue;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
}