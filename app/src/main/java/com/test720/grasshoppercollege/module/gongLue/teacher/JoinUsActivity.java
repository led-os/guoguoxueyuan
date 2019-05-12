package com.test720.grasshoppercollege.module.gongLue.teacher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.SendImgAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.JoinUsCheckData;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.JoinUsData;
import com.test720.grasshoppercollege.myViews.NoScrollGridView;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.isUntil.ISUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.oss.OssService;

public class JoinUsActivity extends BaseToolActivity implements SendImgAdapter.callBack {


    List<JoinUsCheckData> nianjiList = new ArrayList<>();
    List<JoinUsCheckData> keMuList = new ArrayList<>();

    TagAdapter nianJiAdapter;
    TagAdapter keMuAdapter;

    private SendImgAdapter zhengJianAdapter;//证件适配器
    private ArrayList<String> imageList = new ArrayList<>();//证件集
    private ArrayList<String> imageListoss = new ArrayList<>();//证件集

    private String zhengPath = "";//身份证正面路径
    private String zhengPathoss = "";//身份证正面路径
    private String fanPath = "";//身份证反面路径
    private String fanPathoss = "";//身份证反面路径
    //选择照片回调   6证件，，7正面身份，8，反面
    private static final int ZHENGJIAN_CODE = 6;
    private static final int ZHENG_CODE = 7;
    private static final int FAN_CODE = 8;
    private OssService ossService;


    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("apply_for").equals("1")) {
            return "成为教师";
        } else {
            return "成为咨询师";
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_join_us;
    }

    @Override
    protected void initData() {
        ossService = new OssService("Uploads/id_card&cert/", mcontext, getSupportFragmentManager());

        if (getIntent().getStringExtra("apply_for").equals("1")) {
            classLin.setVisibility(View.VISIBLE);
        } else {
            classLin.setVisibility(View.GONE);
        }
        //初始化适配器
        initAdapter();
        HttpParams httpParams = new HttpParams();
        postResponse(HttpUntil.GetIntent().StrategyapplyInfo(), httpParams, 1, true);
    }

    /**
     * @param type 类别
     * @param num  图片数量
     */
    private void getImg(final int type, final int num) {
        ISUntil.getInstance().zhengjian(mcontext, num, type);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case ZHENGJIAN_CODE:
                    List<String> pathList = data.getStringArrayListExtra("result");
                    imageList.addAll(pathList);
                    //最多只保存3张
                    for (int i = 0; i < imageList.size(); i++) {
                        if (i > 2) {
                            imageList.remove(i);
                            ShowToast("最多添加3张照片");
                        }
                    }
                    zhengJianAdapter.notifyDataSetChanged();
                    break;
                case ZHENG_CODE:
                    zhengPath = data.getStringArrayListExtra("result").get(0);
                    Glide.with(mcontext).load(zhengPath).into(addFace);
                    break;
                case FAN_CODE:
                    fanPath = data.getStringArrayListExtra("result").get(0);
                    Glide.with(mcontext).load(fanPath).into(addBack);
                    break;
            }
        }
    }

    /**
     * 年级和科目
     */
    private void initAdapter() {
        nianJiAdapter = new TagAdapter<JoinUsCheckData>(nianjiList) {
            @Override
            public View getView(FlowLayout parent, int position, JoinUsCheckData item) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item.getItem());
                if (item.isCheck()) {
                    textView.setBackgroundResource(R.drawable.bule_back_kuang);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.white_back);
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                }
                return view;
            }
        };
        nianJi.setAdapter(nianJiAdapter);

        keMuAdapter = new TagAdapter<JoinUsCheckData>(keMuList) {
            @Override
            public View getView(FlowLayout parent, int position, JoinUsCheckData item) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item.getItem());
                if (item.isCheck()) {
                    textView.setBackgroundResource(R.drawable.bule_back_kuang);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.white_back);
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                }
                return view;
            }
        };
        keMu.setAdapter(keMuAdapter);

        nianJi.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                nianjiList.get(i).setCheck(!nianjiList.get(i).isCheck());
                nianJiAdapter.notifyDataChanged();
                return true;
            }
        });
        keMu.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                keMuList.get(i).setCheck(!keMuList.get(i).isCheck());
                keMuAdapter.notifyDataChanged();
                return true;
            }
        });
        zhengJianAdapter = new SendImgAdapter(mcontext, imageList, this);
        zhengGrid.setAdapter(zhengJianAdapter);
        // 跳转到图片选择器
        zhengGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imageList.size()) {
                    getImg(ZHENGJIAN_CODE, 3 - imageList.size());
                }
            }
        });
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    JoinUsData joinUsData = new Gson().fromJson(str, JoinUsData.class);
                    nianjiList.clear();

                    for (int i = 0; i < joinUsData.getData().getGrade().size(); i++) {
                        JoinUsCheckData joinUsCheckData = new JoinUsCheckData();
                        joinUsCheckData.setItem(joinUsData.getData().getGrade().get(i));
                        joinUsCheckData.setCheck(false);
                        nianjiList.add(joinUsCheckData);
                    }
                    nianJiAdapter.notifyDataChanged();

                    keMuList.clear();
                    for (int i = 0; i < joinUsData.getData().getSubjects().size(); i++) {
                        JoinUsCheckData joinUsCheckData = new JoinUsCheckData();
                        joinUsCheckData.setItem(joinUsData.getData().getSubjects().get(i));
                        joinUsCheckData.setCheck(false);
                        keMuList.add(joinUsCheckData);
                    }
                    keMuAdapter.notifyDataChanged();
                }
                break;
            case 5:
                if (codeIsOne(str)) {
                    setResult(1, new Intent());
                    finish();
                }
                break;
        }

    }


    @OnClick({R.id.add_face, R.id.add_back, R.id.submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_face:
                getImg(ZHENG_CODE, 1);
                break;
            case R.id.add_back:
                getImg(FAN_CODE, 1);
                break;
            case R.id.submission:
                submissionSend();
                break;
        }
    }

    /***
     * 发布
     */
    private void submissionSend() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请输入您的名字");
            return;
        }
        if (TextUtils.isEmpty(phoneEdit.getText())) {
            phoneEdit.setError("请输入您的电话");
            return;
        }
        if (TextUtils.isEmpty(introduce.getText())) {
            introduce.setError("请输入您的简介");
            return;
        }
        if (zhengPath.equals("")) {
            ShowToast("请上传您的身份证正面");
            return;
        }
        if (fanPath.equals("")) {
            ShowToast("请上传您的身份证反面");
            return;
        }
        if (imageList.size() == 0) {
            ShowToast("请上传您的证件");
            return;
        }
        ossService.beginupload(MyApplication.getmInstance().getUid(), zhengPath, new OssService.SignSendBack() {
            @Override
            public void sucess(String filepath) {
                zhengPathoss = filepath;
                LogUtil.logError("正面完成");
                EventBus.getDefault().post(new CurrencyEvent("zheng", CurrencyEvent.JOINUSIMG));
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
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        //正面传输完成，调起传输反面
        if (currencyEvent.getWhat() == CurrencyEvent.JOINUSIMG && currencyEvent.getMsg().equals("zheng")) {
            ossService.beginupload(MyApplication.getmInstance().getUid(), fanPath, new OssService.SignSendBack() {
                @Override
                public void sucess(String filepath) {
                    fanPathoss = filepath;
                    LogUtil.logError("反面完成");
                    EventBus.getDefault().post(new CurrencyEvent("fan", CurrencyEvent.JOINUSIMG));
                }

                @Override
                public void failure() {

                }

                @Override
                public void onProgressCallback(double progress) {

                }
            });
        }
        //反面传输完成，传输资料
        if (currencyEvent.getWhat() == CurrencyEvent.JOINUSIMG && currencyEvent.getMsg().equals("fan")) {
            ossService.beginUploads(MyApplication.getmInstance().getUid(), imageList, new OssService.SendBack() {
                @Override
                public void sucess(List<String> filepath) {
                    imageListoss.addAll(filepath);
                    LogUtil.logError("证件完成");
                    EventBus.getDefault().post(new CurrencyEvent("imglist", CurrencyEvent.JOINUSIMG));
                }

                @Override
                public void failure() {

                }
            });
        }
        //资料传输完成，通知后台服务器
        if (currencyEvent.getWhat() == CurrencyEvent.JOINUSIMG && currencyEvent.getMsg().equals("imglist")) {
            sendOk();
        }
    }

    private void sendOk() {
        String bookType = getStringBuffer(keMuList);
        String classStr = getStringBuffer(nianjiList);

        if (getIntent().getStringExtra("apply_for").equals("1")) {
            if (bookType == null || bookType.length() == 0) {
                ShowToast("请选择您的科目");
                return;
            }
            if (classStr == null || classStr.length() == 0) {
                ShowToast("请选择您的年级");
                return;
            }
        }

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("name", name.getText().toString());
        httpParams.put("phone", phoneEdit.getText().toString());
        httpParams.put("book_type", bookType);
        httpParams.put("class", classStr);
        httpParams.put("introduce", introduce.getText().toString());
        httpParams.put("apply_for", getIntent().getStringExtra("apply_for"));
        httpParams.put("id_card_z", zhengPathoss);
        httpParams.put("id_card_f", fanPathoss);
        if (imageList.size() >= 1)
            httpParams.put("cert1", imageListoss.get(0));
        if (imageList.size() >= 2)
            httpParams.put("cert2", imageListoss.get(1));
        if (imageList.size() >= 3)
            httpParams.put("cert3", imageListoss.get(2));
        postResponse(HttpUntil.GetIntent().StrategyapplyForTeacher(), httpParams, 5, true);
    }

    /***
     * 获取科目多选字符拼接串
     * @param list  数据
     * @return S
     */
    private String getStringBuffer(List<JoinUsCheckData> list) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                stringBuffer.append(list.get(i).getItem());
                if (i != list.size() - 1) {
                    stringBuffer.append(",");
                }
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public void send(View v, int position) {
        imageList.remove(position);
        zhengJianAdapter.notifyDataSetChanged();
    }


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.keMu)
    TagFlowLayout keMu;
    @BindView(R.id.nianJi)
    TagFlowLayout nianJi;
    @BindView(R.id.zheng_grid)
    NoScrollGridView zhengGrid;
    @BindView(R.id.add_face)
    ImageView addFace;
    @BindView(R.id.add_back)
    ImageView addBack;
    @BindView(R.id.introduce)
    EditText introduce;
    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.classLin)
    LinearLayout classLin;
}
