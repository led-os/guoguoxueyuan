package com.test720.grasshoppercollege.module.userData.geRenZiLiao;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.EditeDialog;
import com.test720.grasshoppercollege.myViews.choosetime.DataPickerPopWindow;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

import static com.test720.grasshoppercollege.R.id.persional_birthday;
import static com.test720.grasshoppercollege.R.id.persional_name;

public class PersonActivity extends BaseToolActivity implements DataPickerPopWindow.PopDataPickerWindow {


    @BindView(R.id.headtext)
    TextView headtext;
    @BindView(R.id.head_image)
    RoundedImageView headImage;
    @BindView(R.id.persionalhead)
    RelativeLayout persionalhead;
    @BindView(R.id.nametext)
    TextView nametext;
    @BindView(persional_name)
    TextView persionalName;
    @BindView(R.id.persionalname)
    RelativeLayout persionalname;
    @BindView(R.id.SEXtext)
    TextView SEXtext;
    @BindView(R.id.sextext)
    TextView sextext;
    @BindView(R.id.persionalsex)
    RelativeLayout persionalsex;
    @BindView(R.id.ritext)
    TextView ritext;
    @BindView(persional_birthday)
    TextView persionalBirthday;
    @BindView(R.id.persionaldb)
    RelativeLayout persionaldb;
    private DataPickerPopWindow dataPickerPopWindow;

    @Override
    protected String setTitle() {
        return "个人资料";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_person;
    }

    @Override
    protected void initData() {
        if (MyApplication.getmInstance().userData != null) {
            persionalName.setText(MyApplication.getmInstance().userData.getData().getNickname());
            persionalBirthday.setText(MyApplication.getmInstance().userData.getData().getBirthday());
            sextext.setText(MyApplication.getmInstance().userData.getData().getSex());
            Glide.with(this).load(MyApplication.getmInstance().userData.getData().getHeader()).error(R.mipmap.pic_head_default).into(headImage);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (!codeIsOne(str)) {
                    persionalName.setText(MyApplication.getmInstance().userData.getData().getNickname());
                }
                break;
            case 2:
                if (!codeIsOne(str)) {
                    sextext.setText(MyApplication.getmInstance().userData.getData().getSex());
                }
                break;
            case 3:
                if (!codeIsOne(str)) {
                    persionalBirthday.setText(MyApplication.getmInstance().userData.getData().getBirthday());
                }
                break;
        }
    }


    @OnClick({R.id.head_image, R.id.persionalhead, R.id.persionalname, R.id.persionalsex, R.id.persionaldb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persionalhead:
            case R.id.head_image:
                jumpToActivity(HeaderActivity.class, false);
                break;
            case R.id.persionalname:
                nickNameDialog();
                break;
            case R.id.persionalsex:
                change_sex();
                break;
            case R.id.persionaldb:
                showDatePopu();
                break;
        }
    }


    /*性别选择*/
    public void change_sex() {

        View contentView = LayoutInflater.from(this).inflate(
                R.layout.sex_check_dialog, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.color.bantouming));
        popupWindow.showAtLocation(persionaldb, Gravity.BOTTOM, 0, 0);

        final TextView photo_album = (TextView) contentView.findViewById(R.id.photo_album);
        final TextView take_pictures = (TextView) contentView.findViewById(R.id.take_pictures);
        final TextView cancle = (TextView) contentView.findViewById(R.id.cancle);
        final TextView popuptitle = (TextView) contentView.findViewById(R.id.popuptitle);
        final TextView diss = (TextView) contentView.findViewById(R.id.diss);
        diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSexToIp(1);
                popupWindow.dismiss();
            }
        });
        take_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSexToIp(0);
                popupWindow.dismiss();
            }
        });

    }

    /*昵称弹框*/
    private void nickNameDialog() {
        EditeDialog dialog = new EditeDialog();
        dialog.setNickName(persionalName.getText().toString());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialog.setOkClick(new EditeDialog.OKClick() {
            @Override
            public void sendToIp(String s) {

                if (s != null && !s.equals("")) {
                    sendToIpOk(s);
                } else {
                    ShowToast("输入昵称不可为空");
                }
            }
        });
        dialog.show(ft, "dialog");
    }

    //昵称
    private void sendToIpOk(String name) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("nickname", name);//请求参数二
        formBuilder.add("type", 2 + "");//请求参数二
        Post(HttpUntil.GetIntent().editInfo(), formBuilder, 1);
        /*更改本地数据*/
        MyApplication.getmInstance().userData.getData().setNickname(name);
        persionalName.setText(name);

    }

    /*性别*/
    private void sendSexToIp(int i) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("sex", i + "");//请求参数二
        formBuilder.add("type", 3 + "");//请求参数二
        Post(HttpUntil.GetIntent().editInfo(), formBuilder, 2);
        if (i == 0) {
            sextext.setText("男");
            MyApplication.getmInstance().userData.getData().setSex("男");
        } else {
            sextext.setText("女");
            MyApplication.getmInstance().userData.getData().setSex("女");
        }
    }

    private int year;
    private int month;
    private int day;

    //修改生日
    private void showDatePopu() {
        Calendar calendar1 = Calendar.getInstance();
        year = calendar1.get(Calendar.YEAR);
        month = calendar1.get(Calendar.MONTH);
        day = calendar1.get(Calendar.DAY_OF_MONTH);
        if (dataPickerPopWindow == null) {
            dataPickerPopWindow = new DataPickerPopWindow(getApplicationContext(), year, month, day);
            dataPickerPopWindow.setOnBirthdayListener(this);
        }
        dataPickerPopWindow.showAtLocation(persionaldb, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
    }

    /*生日*/
    @Override
    public void SaveData(String birthday) {
        if (birthday == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("birthday", birthday);//请求参数二
        formBuilder.add("type", 4 + "");//请求参数二
        Post(HttpUntil.GetIntent().editInfo(), formBuilder, 3);
        persionalBirthday.setText(birthday);
        MyApplication.getmInstance().userData.getData().setBirthday(birthday);
    }
}
