package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.MeiRiYiJuListData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class MeiRiListFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    int type = 1;//1是每日一句，2是填空游戏
    List<MeiRiYiJuListData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    private MediaPlayer mediaPlayer;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        return HttpUntil.GetIntent().EnEveryDayindex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            MeiRiYiJuListData data = new Gson().fromJson(str, MeiRiYiJuListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MeiRiYiJuListData data = new Gson().fromJson(str, MeiRiYiJuListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.MRYJSC) {
            ShuaXin();
        }
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 100 && codeIsOne(str)) {
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.MRYJSC, "每日"));
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page + "");
        httpParams.put("is_collection", getType());
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        return httpParams;
    }

    @Override
    public void init() {

        adapter = new MyBaseRecyclerAdapter<MeiRiYiJuListData.DataBean>(list, getActivity(), R.layout.mei_ri_item) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final MeiRiYiJuListData.DataBean item, final int postion) {
                holder.setText(R.id.juzi, item.getEn());
                holder.setText(R.id.cnJuZi, item.getCn());
                holder.setText(R.id.num, (postion + 1) + "");
                if (item.getIs_collection() == 0) {
                    holder.setImageResource(R.id.shouchang, R.mipmap.aixinsc);
                } else {
                    holder.setImageResource(R.id.shouchang, R.mipmap.aixin2);
                }
                /*听*/
               /* holder.getView(R.id.ting).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playUrl(item.getVoice());
                    }
                });*/
                /*练习 */
                holder.getView(R.id.lianxi).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getmInstance().setLoginDialogIsShow(false);
                        if (MyApplication.getmInstance().getUid().equals("")) {
                            return;
                        }
                        Intent intent = new Intent(getActivity(), MeiRiTianZiActivity.class);
                        intent.putExtra("day_id", item.getDay_id());
                        intent.putExtra("type", "3");
                        jumpToActivity(intent, false);
                    }
                });
                /*游戏*/
                holder.getView(R.id.youxi).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getmInstance().setLoginDialogIsShow(false);
                        if (MyApplication.getmInstance().getUid().equals("")) {
                            return;
                        }
                        Intent intent = new Intent(getActivity(), MeiRiTianZiActivity.class);
                        intent.putExtra("day_id", item.getDay_id());
                        intent.putExtra("type", "2");
                        jumpToActivity(intent, false);
                    }
                });
                /*收藏*/
                holder.getView(R.id.shouchang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getmInstance().setLoginDialogIsShow(false);
                        if (MyApplication.getmInstance().getUid().equals("")) {
                            return;
                        }
                        collection(item.getDay_id());
                        list.get(postion).setIs_collection(list.get(postion).getIs_collection() == 1 ? 0 : 1);
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                Intent intent = new Intent(getActivity(), MeiRiYiJuActivity.class);
                intent.putExtra("day_id", list.get(position).getDay_id());
                intent.putExtra("title", list.get(position).getEn());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    public void playUrl(String url) {
        mediaPlayer = new MediaPlayer();
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare();
            mediaPlayer.start();// prepare自动播放
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("1111111111111111111111", "" + e);
        }
    }

    /*收藏*/
    public void collection(String id) {
        if (id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("day_id", id);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().EnEveryDaycollection(), httpParams, 100, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
