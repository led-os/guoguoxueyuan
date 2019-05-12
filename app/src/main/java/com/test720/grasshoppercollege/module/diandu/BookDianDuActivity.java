package com.test720.grasshoppercollege.module.diandu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;
import com.test720.grasshoppercollege.module.diandu.bean.PointReadingStatus;
import com.test720.grasshoppercollege.module.diandu.downDianDu.CatalogDialog;
import com.test720.grasshoppercollege.myViews.CiDaiBoFangDialog;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.ZoomInTransform;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.test720.grasshoppercollege.R.id.seekBar;

public class BookDianDuActivity extends BaseToolActivity implements CatalogDialog.ListViewClick {


    List<BookFragment> list = new ArrayList<>();
    static final int VOICE_REQUEST_CODE = 66;

    BookDataBean bookDataBean;//书本信息

    private int h;
    private int w;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_dian_du;
    }

    @Override
    public void setSys() {
        super.setSys();
        isFull = true;
    }

    @Override
    protected void initData() {
        //获取屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int px = dpToPx(50, getResources());
        w = dm.widthPixels;
        h = (dm.heightPixels) - px;
        //准备接口bus
        String bookId = getIntent().getStringExtra("book_id");
        BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
        bookDataBean = bookDataInsterUntil.getBook(mcontext, bookId);
        LogUtil.logError(bookDataBean.toString());


        //统计本书总页数
        if (bookDataBean != null) initBook();

        //6.0以上需要权限申请
        requestPermissions();
        viewpager.setPageTransformer(false, new ZoomInTransform());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                if (arg0 == 1) {
                    Log.e(TAG, "onPageScrollStateChanged------>0");
                } else if (arg0 == 0) {
                    Log.e(TAG, "onPageScrollStateChanged------>1");
                } else if (arg0 == 2) {
                   /* list.get(viewpager.getCurrentItem()).getRel().setVisibility(View.VISIBLE);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) != null && i != viewpager.getCurrentItem() && list.get(i).getRel() != null) {
                            list.get(i).getRel().setVisibility(View.INVISIBLE);
                        }
                    }*/
                    Log.e(TAG, "onPageScrollStateChanged------>2");
                }
            }
        });

    }

    private void initBook() {
        for (int i = 0; i < bookDataBean.getData().getContent().size(); i++) {
            BookDataBean.DataBean.ContentBeanX contentBeanX = bookDataBean.getData().getContent().get(i);
            for (int j = 0; j < contentBeanX.getLesson().size(); j++) {//leson层
                BookDataBean.DataBean.ContentBeanX.LessonBean lessonBean = contentBeanX.getLesson().get(j);
                for (int k = 0; k < lessonBean.getPage().size(); k++) {
                    BookDataBean.DataBean.ContentBeanX.LessonBean.PageBean pageBean = lessonBean.getPage().get(k);
                    BookFragment bookFragment = new BookFragment();
                    bookFragment.setW(w);
                    bookFragment.setH(h);
                    bookFragment.setUnit(contentBeanX.getUnit());
                    bookFragment.setLesson(lessonBean.getLesson());
                    bookFragment.setPageBean(pageBean);
                    list.add(bookFragment);
                }
            }
        }
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    /**
     * 点击目录回调方法
     *
     * @param pageIndex 跳转到的目标位置
     */
    @Override
    public void itemClick(int pageIndex) {
        viewpager.setCurrentItem(pageIndex);
    }

    //语速弹框
    private void speedPOP() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            ShowToast("语速调节只支持23以上android系统版本！");
            return;
        }
        @SuppressLint("InflateParams") View contentView = LayoutInflater.from(this).inflate(R.layout.speed_check, null, false);
        final PopupWindow window = new PopupWindow(contentView);
        SeekBar seekbar = contentView.findViewById(seekBar);
        seekbar.setMax(2 * 100);
        int pro = (int) (PointReadingStatus.getInstance().getSpeedF() * 100);
        seekbar.setProgress(pro);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
                Log.v("拖动过程中的值：", String.valueOf(progress) + ", " + String.valueOf(fromUser));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {
                Log.v("开始滑动时的值：", String.valueOf(seekbar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekbar) {
                Log.v("停止滑动时的值：", String.valueOf(seekbar.getProgress()));
                int pro = seekbar.getProgress();
                if (pro < 100) {
                    float f = (30 + (pro * 0.7f));
                    PointReadingStatus.getInstance().setSpeedF(f / 100f);
                } else {
                    PointReadingStatus.getInstance().setSpeedF(pro / 100f);
                }
            }
        });

        window.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bluebantoumingtwo)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(view, 0, -(view.getHeight() + window.getHeight()));
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
//        window.showAtLocation(activity_ke_ben_dian_du, Gravity.LEFT|Gravity.CENTER_VERTICAL, location[0], location[1] - window.getHeight());
        window.showAtLocation(activityRead, Gravity.CENTER, 0, 0);
    }

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == VOICE_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                LogUtil.logError("权限通过");
            } else {
                Toast.makeText(mcontext, "已拒绝权限！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    /**
     * 开启扫描之前判断权限是否打开
     */
    private void requestPermissions() {
        //判断是否开启摄像头权限
        if ((ContextCompat.checkSelfPermission(mcontext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(mcontext,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        ) {
            LogUtil.logError("已开启语音");
            //判断是否开启语音权限
        } else {
            //请求获取摄像头权限
            ActivityCompat.requestPermissions(mcontext,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
        }

    }

    @OnClick({R.id.back, R.id.directory, R.id.danchongfu, R.id.dianduqu, R.id.translate, R.id.speed, R.id.tape})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.directory://目录弹框
                if (bookDataBean == null) return;
                //弱引用目录弹框
                WeakReference<CatalogDialog> weakReference = new WeakReference<>(new CatalogDialog());
                //初始化目录弹框
                CatalogDialog catalogDialog = weakReference.get();
                catalogDialog.setBookDataBean(bookDataBean);
                catalogDialog.setListViewClick(this);
                catalogDialog.setUnit(list.get(viewpager.getCurrentItem()).getUnit());
                catalogDialog.setLesson(list.get(viewpager.getCurrentItem()).getLesson());
                catalogDialog.show(getSupportFragmentManager(), "catalog");

                break;
            case R.id.danchongfu:
                if (PointReadingStatus.getInstance().isDanQu()) {
                    ShowToast("单曲循环已经关闭");
                    PointReadingStatus.getInstance().setDanQu(false);
                    danImg.setImageResource(R.mipmap.fuduguan);
                } else {
                    ShowToast("单曲循环已经打开");
                    PointReadingStatus.getInstance().setDanQu(true);
                    danImg.setImageResource(R.mipmap.fudu);
                }

                break;
            case R.id.dianduqu:
                if (list == null || list.size() == 0) return;
                if (PointReadingStatus.getInstance().isDianDuQu()) {
                    ShowToast("点读区已经关闭");
                    PointReadingStatus.getInstance().setDianDuQu(false);
                    dianduQu.setImageResource(R.mipmap.quyu2);
                } else {
                    ShowToast("点读区已经打开");
                    PointReadingStatus.getInstance().setDianDuQu(true);
                    dianduQu.setImageResource(R.mipmap.quyu);
                }
                break;
            case R.id.translate:
                if (PointReadingStatus.getInstance().isFanYi()) {
                    ShowToast("翻译已经关闭");
                    fanyiImg.setImageResource(R.mipmap.guanfy);
                    PointReadingStatus.getInstance().setFanYi(false);
                } else {
                    ShowToast("翻译已经打开");
                    fanyiImg.setImageResource(R.mipmap.kaifanyi);
                    PointReadingStatus.getInstance().setFanYi(true);
                }

                break;
            case R.id.speed:
                speedPOP();
                break;
            case R.id.tape:
                if (bookDataBean == null) {
                    ShowToast("数据加载中，请稍后");
                    return;
                }
                EventBus.getDefault().post(new CurrencyEvent("磁带播放", CurrencyEvent.DDCDBF));
                WeakReference<CiDaiBoFangDialog> ciDaiBoFangDialogWeakReference = new WeakReference<>(new CiDaiBoFangDialog());
                CiDaiBoFangDialog ciDaiBoFangDialog = ciDaiBoFangDialogWeakReference.get();
                ciDaiBoFangDialog.setUrl(list.get(viewpager.getCurrentItem()).getPageUrl());
                ciDaiBoFangDialog.setSpeedF(PointReadingStatus.getInstance().getSpeedF());
                ciDaiBoFangDialog.show(getSupportFragmentManager(), "ciDai");
                break;
        }
    }

    /**
     * dp转像素
     *
     * @param dp        dp
     * @param resources 资源
     * @return px
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.directory)
    LinearLayout directory;
    @BindView(R.id.danchongfu)
    LinearLayout danchongfu;
    @BindView(R.id.tape)
    LinearLayout tape;
    @BindView(R.id.dianduqu)
    LinearLayout dianduqu;
    @BindView(R.id.translate)
    LinearLayout translate;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.speed)
    LinearLayout speed;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.activity_read)
    RelativeLayout activityRead;
    @BindView(R.id.dan_img)
    ImageView danImg;
    @BindView(R.id.dianduQu)
    ImageView dianduQu;
    @BindView(R.id.fanyiImg)
    ImageView fanyiImg;


}
