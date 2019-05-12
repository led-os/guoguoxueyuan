package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.PaiHangData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class PeiYinPaiHangActivity extends BaseTwoRecyclerViewActivity<PaiHangData.DataBean> {

    View head;
    ViewHolder viewHolder;
    public List<TextView> textlist = new ArrayList<>();
    public List<ImageView> imagelist = new ArrayList<>();
    PaiHangData data;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "本篇排行";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("con_id", getIntent().getStringExtra("con_id"));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().DubbingdubbingRankingList();
            }

            @Override
            public int reCount() {
                return 1;
            }

            @Override
            public RecyclerView reRecyclerView() {
                return recyclerView;
            }

            @Override
            public SwipeRefreshLayout reSwipeRefreshLayout() {
                return swipeRefresh;
            }
        };
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        head = LayoutInflater.from(mcontext).inflate(R.layout.pe_yin_pai_hang, null);
        viewHolder = new ViewHolder(head);
        initHeadClick();

        setAdapter(new MyBaseRecyclerAdapter<PaiHangData.DataBean>(getList(), mcontext, R.layout.pei_yin_pai_hang_item) {

            @Override
            public void convert(BaseRecyclerHolder holder, PaiHangData.DataBean item, int postion) {
                holder.setText(R.id.one_name, item.getNickname());
                holder.setText(R.id.one_level, "lv:" + item.getLevel());
                holder.setText(R.id.num, item.getThumb_up_count());
                holder.setText(R.id.one_price, (postion + 4) + "");
                if (item.getVip() == 1) {
                    holder.setImageResource(R.id.vip_img, R.mipmap.vip);
                } else {
                    holder.setImageResource(R.id.vip_img, R.mipmap.novip);
                }
                holder.setHeaderByUrl(R.id.one_image, item.getHeader());
            }
        });

        getAdapter().setHeaderView(head);

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinPinLunActivity.class);
                intent.putExtra("pei_id", getList().get(position).getPei_id());
                jumpToActivity(intent, true);
            }
        });

        recyclerView.setAdapter(getAdapter());

        textlist.add(viewHolder.oneName);
        textlist.add(viewHolder.twoName);
        textlist.add(viewHolder.threeName);

        imagelist.add(viewHolder.oneImage);
        imagelist.add(viewHolder.twoImage);
        imagelist.add(viewHolder.threeImage);
    }

    /**
     * 顶部点击
     */
    private void initHeadClick() {
        viewHolder.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null && data.getData().size() < 1) {
                    return;
                }
                Intent intent = new Intent(mcontext, PeiYinPinLunActivity.class);
                intent.putExtra("pei_id", data.getData().get(0).getPei_id());
                jumpToActivity(intent, true);
            }
        });
        viewHolder.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null && data.getData().size() < 2) {
                    return;
                }
                Intent intent = new Intent(mcontext, PeiYinPinLunActivity.class);
                intent.putExtra("pei_id", data.getData().get(1).getPei_id());
                jumpToActivity(intent, true);
            }
        });
        viewHolder.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null && data.getData().size() < 3) {
                    return;
                }
                Intent intent = new Intent(mcontext, PeiYinPinLunActivity.class);
                intent.putExtra("pei_id", data.getData().get(2).getPei_id());
                jumpToActivity(intent, true);
            }
        });
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, PaiHangData.class);
            getList().clear();
            if (data.getData().size() > 3) {
                for (int i = 3; i < data.getData().size(); i++) {
                    getList().add(data.getData().get(i));
                }
            }
            getAdapter().notifyDataSetChanged();

            /***前三的信息**/
            for (int i = 0; i < 3; i++) {
                textlist.get(i).setText(data.getData().get(i).getNickname());
                Glide.with(this).load(data.getData().get(i).getHeader()).into(imagelist.get(i));
                switch (i) {
                    case 0:
                        viewHolder.oneZan.setText(data.getData().get(i).getThumb_up_count());
                        break;
                    case 1:
                        viewHolder.twoZan.setText(data.getData().get(i).getThumb_up_count());
                        break;
                    case 2:
                        viewHolder.threeZan.setText(data.getData().get(i).getThumb_up_count());
                        break;
                }
            }
        }

    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PaiHangData data = new Gson().fromJson(str, PaiHangData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    static class ViewHolder {
        @BindView(R.id.two_image)
        RoundedImageView twoImage;
        @BindView(R.id.two_name)
        TextView twoName;
        @BindView(R.id.two_zan)
        TextView twoZan;
        @BindView(R.id.one_image)
        RoundedImageView oneImage;
        @BindView(R.id.one_name)
        TextView oneName;
        @BindView(R.id.one_zan)
        TextView oneZan;
        @BindView(R.id.three_image)
        RoundedImageView threeImage;
        @BindView(R.id.three_name)
        TextView threeName;
        @BindView(R.id.three_zan)
        TextView threeZan;
        @BindView(R.id.one)
        LinearLayout one;
        @BindView(R.id.two)
        LinearLayout two;
        @BindView(R.id.three)
        LinearLayout three;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
