package com.test720.grasshoppercollege;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.Adapter.SendImgAdapter;
import com.test720.grasshoppercollege.myViews.NoScrollGridView;
import com.test720.grasshoppercollege.myViews.emotion.EmotionOneFragment;
import com.test720.grasshoppercollege.myViews.emotion.EmotionThreeFragment;
import com.test720.grasshoppercollege.myViews.emotion.EmotionTwoFragment;
import com.test720.grasshoppercollege.untils.isUntil.ISUntil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.oss.OssService;

public class BasePingJiaActivity extends BaseToolActivity implements SendImgAdapter.callBack {


    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.title_body)
    public TextView titleBody;
    @BindView(R.id.body)
    public EditText body;
    @BindView(R.id.addImg)
    NoScrollGridView addImg;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.biaoqing)
    public ImageView biaoqing;
    @BindView(R.id.baocun)
    TextView baocun;
    @BindView(R.id.imgLin)
    public LinearLayout imgLin;

    @BindView(R.id.gv_emotions)
    ViewPager gvEmotions;
    @BindView(R.id.guideGroup)
    LinearLayout guideGroup;
    @BindView(R.id.down)
    LinearLayout down;
    @BindView(R.id.topLin)
    public LinearLayout topLin;
    private SendImgAdapter mAdapter;
    private static final int REQUEST_CODE = 6;
    private ArrayList<String> imageList = new ArrayList<>();
    private List<String> pathList = new ArrayList<>();
    private int startPos;
    private List<View> guideViewList = new ArrayList<View>();
    //上传图片相关
    OssService ossService;//上传图片框架

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_base_ping_jia;
    }

    @Override
    public void initView() {
        super.initView();
        topLin.setVisibility(View.GONE);
        verifyStoragePermissions(this);
        initViewPager();
        mAdapter = new SendImgAdapter(this, imageList, this);
        addImg.setAdapter(mAdapter);


        // 跳转到图片选择器
        addImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imageList.size()) {
                    ISUntil.getInstance().start(mcontext, 3 - imageList.size(), REQUEST_CODE);
                }
            }
        });

        ossService = new OssService(mcontext, getSupportFragmentManager());
    }

    /*初始化圆点*/
    private void initViewPager() {
        for (int i = 0; i < 3; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.selector_guide_bg);
            view.setSelected(i == startPos ? true : false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources()
                    .getDimensionPixelSize(R.dimen.gudieview_width), getResources().getDimensionPixelSize(R.dimen
                    .gudieview_heigh));
            layoutParams.setMargins(10, 0, 0, 0);
            guideGroup.addView(view, layoutParams);
            guideViewList.add(view);
        }

        gvEmotions.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideViewList.size(); i++) {
                    guideViewList.get(i).setSelected(i == position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        gvEmotions.setCurrentItem(startPos);
        EmotionOneFragment emotionOneFragment = new EmotionOneFragment();
        emotionOneFragment.setBody(body);
        EmotionTwoFragment emotionOneFragment1 = new EmotionTwoFragment();
        emotionOneFragment1.setBody(body);
        EmotionThreeFragment emotionOneFragment2 = new EmotionThreeFragment();
        emotionOneFragment2.setBody(body);
        List<Fragment> list = new ArrayList<>();
        list.add(emotionOneFragment);
        list.add(emotionOneFragment1);
        list.add(emotionOneFragment2);
        gvEmotions.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra("result");
            imageList.addAll(pathList);
            //最多只保存3张
            for (int i = 0; i < imageList.size(); i++) {
                if (i > 2) {
                    imageList.remove(i);
                    ShowToast("最多添加3张照片");
                }

            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initData() {
        if (imgLin.getVisibility() == View.GONE) {
            topLin.setVisibility(View.GONE);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @Override
    public void ThreadHttpBack(String str, int what) {
        super.ThreadHttpBack(str, what);
        if (what == 5) {
            EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.PINGLUNOK, "评论成功"));
        }
    }

    /*发送评论信息到服务器*/
    public void pinLun(List<String> pathList) {

    }

    public void sendFile() {
        ossService.beginUploads(MyApplication.getmInstance().getUid(), pathList, new OssService.SendBack() {
            @Override
            public void sucess(List<String> filepath) {
                pinLun(filepath);
            }

            @Override
            public void failure() {
                ShowToast("文件上传失败,请重新尝试!");
            }
        });
    }

    @Override
    public void send(View v, int position) {
        imageList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    /*表情网格*/
    private void toggleEmotionList(boolean show) {
        down.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @OnClick({R.id.biaoqing, R.id.baocun, R.id.body, R.id.img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.biaoqing:
                toggleEmotionList(down.getVisibility() == View.GONE);
                break;
            case R.id.baocun:
                if (TextUtils.isEmpty(body.getText())) {
                    body.setError("请输入您的想法");
                    return;
                }
                pathList = imageList;
                sendFile();
                break;
            case R.id.body:
                toggleEmotionList(false);
                break;
        }
    }


}
