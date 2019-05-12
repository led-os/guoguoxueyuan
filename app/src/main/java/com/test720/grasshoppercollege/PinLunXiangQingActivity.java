package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.PingLunXiangQingData;
import com.test720.grasshoppercollege.untils.ExpressionUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public abstract class PinLunXiangQingActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.iv_icon)
    RoundedImageView ivIcon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.body)
    public TextView body;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.one)
    View one;
    @BindView(R.id.zan)
    RelativeLayout zan;
    @BindView(R.id.two)
    View two;
    @BindView(R.id.zhuan)
    RelativeLayout zhuan;
    @BindView(R.id.three)
    View three;
    @BindView(R.id.pinlun)
    RelativeLayout pinlun;
    @BindView(R.id.vip)
    ImageView vip;
    @BindView(R.id.oneImg)
    ImageView oneImg;
    @BindView(R.id.twoImg)
    ImageView twoImg;
    @BindView(R.id.threeImg)
    ImageView threeImg;
    @BindView(R.id.pinlunEditText)
    TextView pinlunEditText;
    PingLunXiangQingData data;

    public boolean noZhuan = true;
    List<PingLunXiangQingData.DataBean.ListBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

    @Override
    protected String setTitle() {
        return "评论详情";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pin_lun_xiang_qing;
    }

    @Override
    protected void initData() {
        if (noZhuan) {
            zhuan.setVisibility(View.GONE);
        } else {
            zhuan.setVisibility(View.VISIBLE);
        }
        adapter = new MyBaseRecyclerAdapter<PingLunXiangQingData.DataBean.ListBean>(list, this, R.layout.ping_lun_xiang_qing_children_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PingLunXiangQingData.DataBean.ListBean item, int postion) {
                holder.setHeaderByUrl(R.id.iv_icon, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.time, item.getTime());
                /*评论内容，包含表情*/
                int size = item.getContent().length();
                SpannableString spannableString = ExpressionUtil
                        .getExpressionString(mcontext, item.getContent(), size);
                TextView textView = holder.getView(R.id.body);
                textView.setText(spannableString);

                /*是否vip*/
                if (item.getVip() != null) {
                    if (item.getVip().equals("1")) {
                        holder.setImageResource(R.id.vip, R.mipmap.vip);
                    } else {
                        holder.setImageResource(R.id.vip, R.mipmap.novip);
                    }
                }

            }
        };
        recyclerView.setAdapter(adapter);
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
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, PingLunXiangQingData.class);
            /*评论数，点赞数，分享数*/
            new QBadgeView(this).bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getThumb_up_count())).setBadgeTextSize(7, true);
            new QBadgeView(this).bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getComment_count())).setBadgeTextSize(7, true);
            /*图片*/
            for (int i = 0; i < data.getData().getInfo().getImg().size(); i++) {
                switch (i) {
                    case 0:
                        oneImg.setVisibility(View.VISIBLE);
                        Glide.with(this).load(data.getData().getInfo().getImg().get(i)).into(oneImg);
                        oneImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ImagePagerActivity.startImagePagerActivity(mcontext, data.getData().getInfo().getImg(), 0, new ImagePagerActivity
                                        .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                            }
                        });
                        break;
                    case 1:
                        twoImg.setVisibility(View.VISIBLE);
                        Glide.with(this).load(data.getData().getInfo().getImg().get(i)).into(twoImg);
                        twoImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ImagePagerActivity.startImagePagerActivity(mcontext, data.getData().getInfo().getImg(), 0, new ImagePagerActivity
                                        .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                            }
                        });
                        break;
                    case 2:
                        threeImg.setVisibility(View.VISIBLE);
                        Glide.with(this).load(data.getData().getInfo().getImg().get(i)).into(threeImg);
                        threeImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ImagePagerActivity.startImagePagerActivity(mcontext, data.getData().getInfo().getImg(), 0, new ImagePagerActivity
                                        .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                            }
                        });
                        break;
                }
            }
            if (list.size() == 0) {
                Glide.with(this).load(data.getData().getInfo().getHeader()).error(R.mipmap.pic_head_default).into(ivIcon);
                name.setText(data.getData().getInfo().getNickname());
                time.setText(data.getData().getInfo().getTime());
                /*评论内容，包含表情*/
                int size = data.getData().getInfo().getContent().length();
                SpannableString spannableString = ExpressionUtil
                        .getExpressionString(mcontext, data.getData().getInfo().getContent(), size);
                body.setText(spannableString);
                /*是否vip*/
                if (data.getData().getInfo().getVip() != null) {
                    if (data.getData().getInfo().getVip().equals("1")) {
                        vip.setImageResource(R.mipmap.vip);
                    } else {
                        vip.setImageResource(R.mipmap.novip);
                    }
                }
            }

            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PINGLUNOK && currencyEvent.getMsg().equals("评论成功")) {
            ShuaXin();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PingLunXiangQingData data = new Gson().fromJson(str, PingLunXiangQingData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        if (getIntent().getStringExtra("comment_id") != null)
            builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        builder.add("p", page + "");
        return builder;
    }


    @OnClick({R.id.pinlunEditText, R.id.zan, R.id.zhuan, R.id.pinlun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                dianZan();
                break;
            case R.id.zhuan:
                break;
            case R.id.pinlun:
            case R.id.pinlunEditText:
                pingLun();
                break;
        }
    }

    public abstract void dianZan();

    public abstract void pingLun();

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5) codeIsOne(str);
    }


}