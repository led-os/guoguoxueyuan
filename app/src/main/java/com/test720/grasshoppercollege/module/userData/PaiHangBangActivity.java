package com.test720.grasshoppercollege.module.userData;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.PaiHangBangData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class PaiHangBangActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_pai_hang_bang)
    LinearLayout activityPaiHangBang;
    @BindView(R.id.quyu)
    ImageView quyu;

    MyBaseRecyclerAdapter adapter;

    @BindView(R.id.one_pic)
    RoundedImageView onePic;
    @BindView(R.id.one_name)
    TextView oneName;
    @BindView(R.id.one_pains)
    TextView onePains;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two_pic)
    RoundedImageView twoPic;
    @BindView(R.id.two_name)
    TextView twoName;
    @BindView(R.id.two_pains)
    TextView twoPains;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.three_pic)
    RoundedImageView threePic;
    @BindView(R.id.three_name)
    TextView threeName;
    @BindView(R.id.three_pains)
    TextView threePains;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.pic)
    RoundedImageView pic;
    @BindView(R.id.name)
    TextView name;
    int type = 1;//排行类型
    List<PaiHangBangData.DataBean.ListBean> list = new ArrayList<>();
    @BindView(R.id.mynum)
    TextView mynum;

    @Override
    protected String setTitle() {
        return "果果豆排行榜";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pai_hang_bang;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<PaiHangBangData.DataBean.ListBean>(list, this, R.layout.pai_hang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PaiHangBangData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.num, (postion + 4) + "");
                holder.setText(R.id.name, item.getNickname());
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                holder.setText(R.id.doudou_num, item.getPoints());
            }

        };
        recyclerView.setAdapter(adapter);


        /****个人*/
        if (MyApplication.getmInstance().userData != null) {
            mynum.setText(MyApplication.getmInstance().userData.getData().getPoints());
            GlideUntil.getInstance().headByUrl(mcontext, pic, MyApplication.getmInstance().userData.getData().getHeader());
            name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        }


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
        return HttpUntil.GetIntent().rankingList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            PaiHangBangData data = new Gson().fromJson(str, PaiHangBangData.class);
            num.setText(data.getData().getInfo().getRanking() + "");
            list.clear();
            Top(data);
            if (data.getData().getList().size() > 3) {
                for (int i = 3; i < data.getData().getList().size(); i++) {
                    list.add(data.getData().getList().get(i));
                }
                adapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PaiHangBangData data = new Gson().fromJson(str, PaiHangBangData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    /*前三名*/
    private void Top(PaiHangBangData data) {
        for (int i = 0; i < data.getData().getList().size(); i++) {
            switch (i) {
                case 0:
                    one.setVisibility(View.VISIBLE);
                    oneName.setText(data.getData().getList().get(i).getNickname());
                    onePains.setText(data.getData().getList().get(i).getPoints());
                    Glide.with(this).load(data.getData().getList().get(i).getHeader()).placeholder(R.mipmap.pic_head_default).error(R.mipmap.pic_head_default).into(onePic);
                    break;
                case 1:
                    two.setVisibility(View.VISIBLE);
                    twoName.setText(data.getData().getList().get(i).getNickname());
                    twoPains.setText(data.getData().getList().get(i).getPoints());
                    Glide.with(this).load(data.getData().getList().get(i).getHeader()).placeholder(R.mipmap.pic_head_default).error(R.mipmap.pic_head_default).into(twoPic);
                    break;
                case 2:
                    three.setVisibility(View.VISIBLE);
                    threeName.setText(data.getData().getList().get(i).getNickname());
                    threePains.setText(data.getData().getList().get(i).getPoints());
                    Glide.with(this).load(data.getData().getList().get(i).getHeader()).placeholder(R.mipmap.pic_head_default).error(R.mipmap.pic_head_default).into(threePic);
                    break;
            }

        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("p", page + "");//请求参数
        formBuilder.add("type", type + "");//请求参数
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数

        return formBuilder;
    }


    @OnClick(R.id.quyu)
    public void onClick() {

        View view = LayoutInflater.from(this).inflate(R.layout.paihangquyu_item, null);
        final PopupWindow pop = new PopupWindow(view);
        view.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                ShuaXin();
                pop.dismiss();
            }
        });
        view.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                ShuaXin();
                pop.dismiss();
            }
        });
        view.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 3;
                ShuaXin();
                pop.dismiss();
            }
        });
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.touming)));
        pop.setOutsideTouchable(true);
        pop.setTouchable(true);
        int[] xy = new int[2];
        quyu.getLocationOnScreen(xy);
        pop.showAtLocation(activityPaiHangBang, Gravity.NO_GRAVITY, xy[0] + pop.getWidth() + quyu.getWidth(), xy[1]);
    }


}
