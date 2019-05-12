package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.oss.OssService;

public class LaunchAnswerActivity extends BaseToolActivity {
    LuYinUntil luYinUntil;
    String filePath = "";//录音地址
    MusicUntil musicUntil;
    String price = "";//价格
    String answerId = "";// 发布问题的id
    OssService ossService;
    TextDialog textDialog;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_launch_answer;
    }

    @Override
    protected void initData() {
        ossService = new OssService("Uploads/consulting_voice/user/", mcontext, getSupportFragmentManager());
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
                return yuYin;
            }
        });
        musicUntil = new MusicUntil(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        }, 1f);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    finish();
                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    finish();
                } else {
//                    pay();
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.send, R.id.yuYin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
                launchQuestion();
                break;
            case R.id.yuYin:
                musicUntil.playMusic(filePath);
                break;
        }
    }

    /**
     * 调起余额支付
     */

    private void pay() {
        //余额支付
        PlayByYuEDialog dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(price);
        dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
            @Override
            public void playOk(String pwd) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("password", pwd);
                builder.add("answer_id", answerId);
                Post(HttpUntil.GetIntent().payAnswerQuestion(), builder, 2);
            }

            @Override
            public void close() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("answer_id", answerId);
                Post(HttpUntil.GetIntent().payAnswerQuestion(), builder, 5);
            }
        });
        dialog.show(getSupportFragmentManager(), "play");
    }


    /**
     * 发布问题
     */
    private void launchQuestion() {
        if (TextUtils.isEmpty(edit.getText().toString()) && filePath.equals("")) {
            ShowToast("请用语言或者文字描述你想要咨询的问题!");
            return;
        }
        if (textDialog == null) {
            textDialog = new TextDialog();
            textDialog.setTextStr("请到攻略--家长--我的问答--待回复里等待老师解答哦！");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    ossService.beginupload(MyApplication.getmInstance().getUid(), filePath, new OssService.SignSendBack() {
                        @Override
                        public void sucess(String filepath) {
                            HttpParams httpParams = new HttpParams();
                            httpParams.put("tid", getIntent().getStringExtra("tid"));
                            httpParams.put("uid", MyApplication.getmInstance().getUid());
                            if (!TextUtils.isEmpty(edit.getText()))
                                httpParams.put("question", edit.getText().toString());
                            if (filepath != null && !filepath.equals("")) {
                                if (!TextUtils.isEmpty(yuYin.getText()))
                                    httpParams.put("question_voice_time", yuYin.getText().toString());
                                httpParams.put("file", filepath);
                            }
                            postResponse(HttpUntil.GetIntent().consultingQuestion(), httpParams, 1, true);
                        }

                        @Override
                        public void failure() {

                        }

                        @Override
                        public void onProgressCallback(double progress) {

                        }
                    });
                }

                @Override
                public void no() {

                }
            });
        }
        textDialog.show(getSupportFragmentManager(), "text");
    }


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.yuYin)
    TextView yuYin;
    @BindView(R.id.lu_yin)
    Button luYin;
    @BindView(R.id.root)
    LinearLayout root;
}
