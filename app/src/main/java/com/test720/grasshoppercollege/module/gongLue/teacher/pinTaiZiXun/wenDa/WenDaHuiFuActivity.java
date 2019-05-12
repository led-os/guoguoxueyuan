package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.WenDaXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.oss.OssService;

public class WenDaHuiFuActivity extends BaseToolActivity {
    LuYinUntil luYinUntil;
    String filePath = "";//录音地址
    String userpath = "";//家长录音地址
    MusicUntil musicUntil;

    OssService ossService;

    public final static int WENDA = 5;  //问答列表
    public final static int TEACHER_DHF = 4;//老师待回复
    public final static int TEACHER_YHF = 3;//老师已回复
    public final static int JIAZHANG_DHF = 2;//家长待回复
    public final static int JIAZHANG_YHF = 1;//家长已回复

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_wen_da_hui_fu;
    }

    /**
     * 不可编辑
     */
    private void can_not_edit() {
        downFram.setVisibility(View.GONE);
        huiFuEdit.setCursorVisible(false);
        huiFuEdit.setFocusable(false);
        huiFuEdit.setFocusableInTouchMode(false);
        huiFu.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {

        switch (getIntent().getIntExtra("type", 5)) {
            case TEACHER_DHF:
                downFram.setVisibility(View.VISIBLE);
                huiFu.setVisibility(View.VISIBLE);
                title.setText("待回复");
                ossService = new OssService("Uploads/consulting_voice/teacher/", mcontext, getSupportFragmentManager());
                break;
            case TEACHER_YHF:
                can_not_edit();
                title.setText("已回复");
                teacherLin.setVisibility(View.VISIBLE);
                break;
            case JIAZHANG_DHF:
                can_not_edit();
                title.setText("待回复");
                teacherLin.setVisibility(View.GONE);
                break;
            case JIAZHANG_YHF:
                can_not_edit();
                title.setText("已回复");
                teacherLin.setVisibility(View.VISIBLE);
                break;
            case WENDA:
                can_not_edit();
                teacherLin.setVisibility(View.VISIBLE);
                title.setText("问答详情");
        }


        luYinUntil = new LuYinUntil(mcontext, new LuYinUntil.LuYinInterface() {
            @Override
            public View root() {
                return root;
            }

            @Override
            public View changAnView() {
                return luYin;
            }

            @Override
            public void filePath(String path) {
                filePath = path;
            }

            @Override
            public TextView shuChuTime() {
                return teacherYuYin;
            }
        });
        musicUntil = new MusicUntil(1f);

        HttpParams httpParams = new HttpParams();
        httpParams.put("answer_id", getIntent().getStringExtra("answer_id"));
        postResponse(HttpUntil.GetIntent().StrategyanswerOrderInfo(), httpParams, 1, true);

    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                WenDaXiangQingData daXiangQingData = new Gson().fromJson(str, WenDaXiangQingData.class);
                name.setText(daXiangQingData.getData().getUser_nickname());
                time.setText(daXiangQingData.getData().getQuestion_time());
                GlideUntil.getInstance().headByUrl(mcontext, pic, daXiangQingData.getData().getUser_header());
                question.setText(daXiangQingData.getData().getQuestion());
                userpath = daXiangQingData.getData().getQuestion_voice();
                userYuYin.setText(daXiangQingData.getData().getQuestion_voice_time());
                huiFuEdit.setText(daXiangQingData.getData().getAnswer());

                //首先初始化数据为网络返回
                if (daXiangQingData.getData().getAnswer_voice_time() != null) {
                    teacherYuYin.setText(daXiangQingData.getData().getAnswer_voice_time());
                }
                if (daXiangQingData.getData().getAnswer_voice() != null) {
                    filePath = daXiangQingData.getData().getAnswer_voice();
                }
                if (daXiangQingData.getData().getAnswer_voice_time() != null) {
                    teacherYuYin.setText(daXiangQingData.getData().getAnswer_voice_time());
                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    setResult(1, new Intent());
                    finish();
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @OnClick({R.id.back, R.id.huiFu, R.id.user_yu_yin, R.id.teacher_yu_yin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.huiFu:
                huiFuUser();
                break;
            case R.id.user_yu_yin:
                if (userpath.equals("")) {
                    ShowToast("无音频文件");
                    return;
                }
                final AnimationDrawable am = (AnimationDrawable) userPo.getDrawable();
//                musicUntil.playHaveAm(userpath, am);
                musicUntil.setStartListener(new MusicUntil.StartListener() {
                    @Override
                    public void startPlay() {
                        am.start();
                    }
                });
                musicUntil.setEndComListener(new MusicUntil.EndComListener() {
                    @Override
                    public void endListener() {
                        am.stop();
                    }
                });
                musicUntil.playMusic(userpath);
                break;
            case R.id.teacher_yu_yin:
                if (filePath.equals("")) {
                    ShowToast("无音频文件");
                    return;
                }
                final AnimationDrawable tam = (AnimationDrawable) teacherPo.getDrawable();
                musicUntil.setStartListener(new MusicUntil.StartListener() {
                    @Override
                    public void startPlay() {
                        tam.start();
                    }
                });
                musicUntil.setEndComListener(new MusicUntil.EndComListener() {
                    @Override
                    public void endListener() {
                        tam.stop();
                    }
                });
                musicUntil.playMusic(filePath);

                break;
        }
    }

    private void huiFuUser() {
        if (TextUtils.isEmpty(huiFuEdit.getText()) && filePath.equals("")) {
            ShowToast("请用语言或者文字描述回复问题!");
            return;
        }
        ossService.beginupload(MyApplication.getmInstance().getUid(), filePath, new OssService.SignSendBack() {
            @Override
            public void sucess(String filepath) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("answer_id", getIntent().getStringExtra("answer_id"));
                if (!TextUtils.isEmpty(huiFuEdit.getText()))
                    httpParams.put("answer", huiFuEdit.getText().toString());
                if (!TextUtils.isEmpty(teacherYuYin.getText()))
                    httpParams.put("answer_voice_time", teacherYuYin.getText().toString());
                if (filepath != null && !filepath.equals("")) httpParams.put("file", filepath);
                postResponse(HttpUntil.GetIntent().StrategyreplyAnswerOrder(), httpParams, 2, true);
            }

            @Override
            public void failure() {

            }

            @Override
            public void onProgressCallback(double progress) {

            }
        });


    }

    @BindView(R.id.user_yu_yin)
    TextView userYuYin;
    @BindView(R.id.teacher_po)
    ImageView teacherPo;
    @BindView(R.id.user_po)
    ImageView userPo;
    @BindView(R.id.huiFu)
    TextView huiFu;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.teacherLin)
    LinearLayout teacherLin;
    @BindView(R.id.pic)
    RoundedImageView pic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.hui_fu_edit)
    EditText huiFuEdit;
    @BindView(R.id.teacher_yu_yin)
    TextView teacherYuYin;
    @BindView(R.id.lu_yin)
    TextView luYin;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.downFram)
    FrameLayout downFram;

}
