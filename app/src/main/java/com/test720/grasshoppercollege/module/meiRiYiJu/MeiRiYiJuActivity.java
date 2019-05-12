package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.media.MediaPlayer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.MeiRiYiJuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.sharePage.ShareUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.oss.OssService;


public class MeiRiYiJuActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<MeiRiYiJuData.DataBean.ListBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.laba)
    ImageView laba;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.en)
    TextView en;
    @BindView(R.id.cn)
    TextView cn;
    @BindView(R.id.bo)
    TextView bo;
    @BindView(R.id.chongLu)
    TextView chongLu;
    @BindView(R.id.my_lu_yin_Lin)
    RelativeLayout myLuYinLin;
    @BindView(R.id.luYinImg)
    Button luYinImg;

    //播放器
    MusicUntil musicUntil;
    /*录音相关*/
    String path;
    MeiRiYiJuData data;
    //传文件
    OssService ossService;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mei_ri_yi_ju;
    }

    @Override
    protected void initData() {
        ossService = new OssService("Uploads/every_day/user/", mcontext, getSupportFragmentManager());

        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        }
        adapter = new MyBaseRecyclerAdapter<MeiRiYiJuData.DataBean.ListBean>(list, this, R.layout.mei_ri_pin_lun_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final MeiRiYiJuData.DataBean.ListBean item, final int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.num, item.getLike_count() + "人喜欢");
                holder.setText(R.id.bo, item.getVoice_time());

                if (item.getIs_like().equals("0")) {
                    holder.setImageResource(R.id.shouchang, R.mipmap.aixinsc);
                } else {
                    holder.setImageResource(R.id.shouchang, R.mipmap.aixin2);
                }
                /*喜欢*/
                holder.getView(R.id.shouchang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        collection(item.getU_day_id());
                        list.get(postion).setIs_like(list.get(postion).getIs_like().equals("1") ? "0" : "1");
                    }
                });

                final ImageView imageView = holder.getView(R.id.luYinImg);

                /*听*/
                holder.getView(R.id.bo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        musicUntil.playHaveAm(item.getVoice(), imageView);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
        musicUntil = new MusicUntil(1f);
        new LuYinUntil(mcontext, new LuYinUntil.LuYinInterface() {
            @Override
            public View root() {
                return swipeRefresh;
            }

            @Override
            public View changAnView() {
                return luYinImg;
            }

            @Override
            public void filePath(String luYinPath) {
                path = luYinPath;
                myLuYinLin.setVisibility(View.VISIBLE);
                luYinImg.setVisibility(View.GONE);
            }

            @Override
            public TextView shuChuTime() {
                return bo;
            }
        });
    }

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnEveryDaydayInfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, MeiRiYiJuData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
            cn.setText(data.getData().getInfo().getCn());
            en.setText(data.getData().getInfo().getEn());
            Glide.with(this).load(data.getData().getInfo().getPicture()).into(pic);

        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MeiRiYiJuData data = new Gson().fromJson(str, MeiRiYiJuData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ifNoData() {

    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("day_id", getIntent().getStringExtra("day_id"));
        builder.add("p", page + "");
        builder.add("uid", MyApplication.getmInstance().getUid() + "");
        return builder;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 100 || what == 1001) {
            codeIsOne(str);
            ShuaXin();
        }
    }


    /*喜欢*/
    public void collection(String id) {
        if (id == null) return;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("u_day_id", id);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().EnEveryDaylike(), builder, 100, false);
    }

    /*发表*/
    public void send() {
        if (path == null) {
            ShowToast("请先录音");
            return;
        }
        ossService.beginupload(MyApplication.getmInstance().getUid(), path, new OssService.SignSendBack() {
            @Override
            public void sucess(String filepath) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("day_id", getIntent().getStringExtra("day_id"));
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("voice_time", bo.getText().toString());
                httpParams.put("file", filepath);
                postResponse(HttpUntil.GetIntent().EnEveryDayaddSound(), httpParams, 1001, true);
                path = null;
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
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.SHAREOK) {
            if (event.getMsg().equals("分享成功")) {

            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @OnClick({R.id.laba, R.id.back, R.id.share, R.id.lu_yin_rel, R.id.bo, R.id.pic, R.id.chongLu, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pic:
            case R.id.laba:
                if (data == null) return;
                //如果不是播放的音频路径，则加载音频路径
                if (!musicUntil.getUrl().equals(data.getData().getInfo().getVoice())) {
                    musicUntil.playMusic(data.getData().getInfo().getVoice());
                    musicUntil.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            laba.setImageResource(R.mipmap.bofang1);
                        }
                    });
                    laba.setImageResource(R.mipmap.zant);
                    return;
                }
                if (musicUntil.pauseOrStart()) {
                    laba.setImageResource(R.mipmap.bofang1);
                } else {
                    laba.setImageResource(R.mipmap.zant);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.share:
                ShareUntil.getInstance().share(12, "蝈蝈学苑", "每日一句", mcontext);
                break;
            case R.id.lu_yin_rel:
                /*播放录音显示时，不显示录音按钮*/
                myLuYinLin.setVisibility(View.GONE);
                luYinImg.setVisibility(View.VISIBLE);
                break;
            case R.id.bo:
                if (path != null) {
                    musicUntil.playMusic(path);
                }
                break;
            case R.id.chongLu:
                luYinImg.setVisibility(View.VISIBLE);
                myLuYinLin.setVisibility(View.GONE);
                break;
            case R.id.send:
                send();
                luYinImg.setVisibility(View.GONE);
                myLuYinLin.setVisibility(View.GONE);
                break;
        }
    }
}
