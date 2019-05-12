package com.test720.grasshoppercollege.module.shangCheng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BasePlayActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.bean.QueRenDingDanData;
import com.test720.grasshoppercollege.module.userData.MyAdressActivity;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.YouHuiJuanChooseActivity;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.myViews.ViewAmount;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

import static com.test720.grasshoppercollege.R.id.total_price;

public class QueRenDingDanActivity extends BasePlayActivity {

    String listId;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(total_price)
    TextView totalPrice;
    @BindView(R.id.shipping_price_text)
    TextView shippingPriceText;
    MyBaseRecyclerAdapter adapter;
    List<QueRenDingDanData.DataBean.ListBean> list = new ArrayList<>();
    HeadHolder headHolder;
    FootHolder footHolder;
    View head;
    View foot;
    QueRenDingDanData dingDanData;
    String playType = "1";//充值方式 1，支付宝，2微信, 3余额
    private String cid = "";//优惠劵id
    private String adressId = null;//地址id
    float youHuiJInE = 0;//优惠劵抵用金额
    float orderJinE = 0;//订单金额
    float douDouJinE = 0;//豆豆抵用金额
    private String gids = "";//商品id

    @Override
    protected String setTitle() {
        return "确认订单";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_que_ren_ding_dan;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);

        listId = getIntent().getStringExtra("cart_id");
        head = LayoutInflater.from(mcontext).inflate(R.layout.que_ren_ding_dan_head_one, null);
        headHolder = new HeadHolder(head);
        foot = LayoutInflater.from(mcontext).inflate(R.layout.que_ren_ding_dan_foot, null);
        footHolder = new FootHolder(foot);
        footHolder.viewAmount.setMinNum(0);
        footHolder.viewAmount.setAmount(0);
        footHolder.viewAmount.setTiShiYu("果果豆使用已达上限!");
        initListener();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                getData();
            }
        });
        getData();
    }


    @Override
    public float getJinE() {
        return Float.parseFloat(totalPrice.getText().toString());
    }

    @Override
    public String getPlayWay() {
        return playType;
    }

    /*添加事件*/
    private void initListener() {
        /*选择地址*/
        headHolder.addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dingDanData == null) return;
                Intent intent = new Intent(mcontext, MyAdressActivity.class);
                startActivityForResult(intent, 123);
            }
        });
        /*选择地址*/
        headHolder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dingDanData == null) return;
                Intent intent = new Intent(mcontext, MyAdressActivity.class);
                startActivityForResult(intent, 123);
            }
        });
        footHolder.zhifubaoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type(1);
            }
        });
        footHolder.weixinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type(2);
            }
        });
        footHolder.yeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type(3);
            }
        });
        footHolder.viewAmount.setOnAmountChangeListener(new ViewAmount.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                if (dingDanData != null) {
                    //果果豆抵扣的金额
                    douDouJinE = (float) amount / (Float.parseFloat(dingDanData.getData().getPoints_pro()));
                    footHolder.douText.setText("-￥" + douDouJinE);
                    priceChange();
                }
            }
        });
        footHolder.juanRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, YouHuiJuanChooseActivity.class);
                intent.putExtra("money", totalPrice.getText().toString());
                intent.putExtra("coupon_type", "3");
                intent.putExtra("gids", gids);
                startActivityForResult(intent, 1);
            }
        });

    }

    /*支付类型选择*/
    private void type(int i) {
        footHolder.staus1.setImageResource(R.mipmap.huiq);
        footHolder.staus2.setImageResource(R.mipmap.huiq);
        footHolder.staus3.setImageResource(R.mipmap.huiq);
        playType = i + "";
        if (i == 1) {
            footHolder.staus1.setImageResource(R.mipmap.goux);
        } else if (i == 2) {
            footHolder.staus2.setImageResource(R.mipmap.goux);
        } else if (i == 3) {
            footHolder.staus3.setImageResource(R.mipmap.goux);
        }
    }

    /*刷新数据*/
    public void getData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        if (listId != null) builder.add("cart_id", listId.toString());
        if (getIntent().getStringExtra("order_num") != null) {
            setOrdernum(getIntent().getStringExtra("order_num"));
            builder.add("order_num", getIntent().getStringExtra("order_num"));
        }
        if (getIntent().getStringExtra("gid") != null) {
            builder.add("gid", getIntent().getStringExtra("gid"));
        }
        if (getIntent().getStringExtra("value") != null) {
            builder.add("value", getIntent().getStringExtra("value"));
        }
        if (getIntent().getStringExtra("count") != null) {
            builder.add("count", getIntent().getStringExtra("count"));
        }
        Post(HttpUntil.GetIntent().MallconfirmOrder(), builder, 1);
    }

    /**
     * 获取支付订单号,
     */
    private void playEnvent() {
        if (payTypeCheck()) return;
        //已经获得支付订单后直接调起支付
        if (getOrdernum() != null) {
            afterOrder();
            return;
        }
        if (adressId == null) {
            ShowToast("请设置收货地址");
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        if (listId != null) builder.add("cart_id", listId);
        builder.add("points", footHolder.viewAmount.getAmount() + "");
        if (cid != null) builder.add("user_cid", cid);
        if (TextUtils.isEmpty(footHolder.beiZhu.getText())) {
            builder.add("user_remark", "");
        } else {
            builder.add("user_remark", footHolder.beiZhu.getText().toString());
        }
        builder.add("address_id", adressId);
        if (getIntent().getStringExtra("gid") != null) {
            builder.add("gid", getIntent().getStringExtra("gid"));
        }
        if (getIntent().getStringExtra("value") != null) {
            builder.add("value", getIntent().getStringExtra("value"));
        }
        if (getIntent().getStringExtra("count") != null) {
            builder.add("count", getIntent().getStringExtra("count"));
        }
        Post(HttpUntil.GetIntent().ordercreateOrder(), builder, 10001);
    }

    /**
     * 总价变动
     */
    public void priceChange() {
        float tp = orderJinE - youHuiJInE - douDouJinE;//实际金额
        tp = siSheWuRu(tp);
        totalPrice.setText(tp + "");
        footHolder.juanText.setText("-￥" + youHuiJInE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*地址选择回返*/
        if (requestCode == 123 && resultCode == 1) {
            adressId = data.getStringExtra("id");
            headHolder.addresstext.setText(data.getStringExtra("adress"));
            headHolder.nametext.setText("收货人:" + data.getStringExtra("name"));
            headHolder.phonetext.setText(data.getStringExtra("phone"));


            headHolder.addAddress.setVisibility(View.GONE);
            headHolder.address.setVisibility(View.VISIBLE);
        } else if (requestCode == 123) {
            getData();
        }

        //获得优惠劵id
        if (requestCode == 1 && resultCode == 2) {
            cid = data.getStringExtra("id");
            String p = data.getStringExtra("price");
            youHuiJInE = Float.parseFloat(p);
            priceChange();
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PAYOK) {//付款成功后发送通知
            EventBus.getDefault().post(new CurrencyEvent("付款完成", CurrencyEvent.MYORDER));
            Intent intent = new Intent(mcontext, ShangChengActivity.class);
            intent.putExtra("pay", "1");
            jumpToActivity(intent, true);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    dingDanData = new Gson().fromJson(str, QueRenDingDanData.class);
                    //拼接商品id
                    gids = "";
                    for (int i = 0; i < dingDanData.getData().getList().size(); i++) {
                        if (i == 0) gids = gids + dingDanData.getData().getList().get(i).getGid();
                        else gids = gids + "," + dingDanData.getData().getList().get(i).getGid();
                    }
                    //商品数量
                    int spnum = 0;
                    for (int i = 0; i < dingDanData.getData().getList().size(); i++) {
                        int j = Integer.parseInt(dingDanData.getData().getList().get(i).getCount());
                        spnum = spnum + j;
                    }
                    footHolder.shuliang.setText(spnum + "");

                    list.clear();
                    list.addAll(dingDanData.getData().getList());
                    adapter = new MyBaseRecyclerAdapter<QueRenDingDanData.DataBean.ListBean>(list, mcontext, R.layout.que_ren_ding_dan_item) {
                        @Override
                        public void convert(BaseRecyclerHolder holder, QueRenDingDanData.DataBean.ListBean item, int postion) {
                            holder.setImageByUrl(R.id.small_item_image, item.getCover());
                            holder.setText(R.id.small_number, "*" + item.getCount());
                            holder.setText(R.id.value, item.getValue());
                            holder.setText(R.id.small_price, "￥" + item.getPrice());
                            holder.setText(R.id.vip_price, "vip￥" + item.getVip_price());
                            holder.setText(R.id.samlltitle, item.getName());
                        }
                    };
                    initHead();
                    initFoot();

                    adapter.setHeaderView(head);
                    adapter.setmFoot(foot);
                    recyclerView.setAdapter(adapter);
                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        String status = jsonObject.getJSONObject("data").getString("status");
                        switch (status) {
                            case "1"://成功
                                EventBus.getDefault().post(new CurrencyEvent("付款完成", CurrencyEvent.PAYOK));
                                break;

                            case "3"://密码错误
                                dialog.show(getSupportFragmentManager(), "play");
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    PlayByYuEDialog dialog;//余额支付密码弹框

    /***
     * 四舍五入
     * @return 四舍五入后数据
     */
    private float siSheWuRu(float ft) {
        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd = new BigDecimal((double) ft);
        bd = bd.setScale(scale, roundingMode);
        ft = bd.floatValue();
        return ft;
    }

    @Override
    public void yuEZhiFu(final String ordernum) {
        //余额支付
        dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(totalPrice.getText().toString());
        dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
            @Override
            public void playOk(String pwd) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("password", pwd);
                builder.add("order_num", ordernum);
                Post(HttpUntil.GetIntent().paybalancePayOrder(), builder, 2);
            }

            @Override
            public void close() {

            }
        });
        dialog.show(getSupportFragmentManager(), "play");
    }

    /*填充头部数据*/
    private void initHead() {
        if (dingDanData.getData().getAddress() == null) {
            headHolder.addAddress.setVisibility(View.VISIBLE);
            headHolder.address.setVisibility(View.GONE);
            adressId = null;
            return;
        }
        headHolder.addAddress.setVisibility(View.GONE);
        headHolder.address.setVisibility(View.VISIBLE);

        adressId = dingDanData.getData().getAddress().getAddress_id();
        headHolder.nametext.setText("收货人:" + dingDanData.getData().getAddress().getConsignee());
        headHolder.phonetext.setText(dingDanData.getData().getAddress().getPhone());
        headHolder.addresstext.setText(dingDanData.getData().getAddress().getProvince() +
                dingDanData.getData().getAddress().getCity() +
                dingDanData.getData().getAddress().getArea() +
                dingDanData.getData().getAddress().getOther());

    }

    /*填充尾部数据*/
    private void initFoot() {
        footHolder.yunFeiDece.setText(dingDanData.getData().getMin_price());
        footHolder.yunFei.setText(dingDanData.getData().getExpress_fee());
        orderJinE = dingDanData.getData().getOrder_price();
        footHolder.zongjia.setText("￥" + orderJinE);
        totalPrice.setText(orderJinE + "");

        int point = Integer.parseInt(dingDanData.getData().getPoints());
        int usepoint = Integer.parseInt(dingDanData.getData().getUse_points());
        int p = point > usepoint ? usepoint : point;
        footHolder.viewAmount.setGoods_storage(p);


        String f = "(含运费￥" + dingDanData.getData().getExpress_fee() + ")";
        shippingPriceText.setText(f);
    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        playEnvent();
    }

    static class HeadHolder {
        @BindView(R.id.nametext)
        TextView nametext;
        @BindView(R.id.phonetext)
        TextView phonetext;
        @BindView(R.id.addresstext)
        TextView addresstext;
        @BindView(R.id.address)
        RelativeLayout address;
        @BindView(R.id.add_address)
        RelativeLayout addAddress;

        HeadHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class FootHolder {
        @BindView(R.id.yunFeiDece)
        TextView yunFeiDece;
        @BindView(R.id.yunFei)
        TextView yunFei;
        @BindView(R.id.shuliang)
        TextView shuliang;
        @BindView(R.id.zongjia)
        TextView zongjia;
        @BindView(R.id.youHuiJuan)
        RelativeLayout youHuiJuan;
        @BindView(R.id.staus1)
        ImageView staus1;
        @BindView(R.id.zhifubao_layout)
        RelativeLayout zhifubaoLayout;
        @BindView(R.id.staus2)
        ImageView staus2;
        @BindView(R.id.weixin_layout)
        RelativeLayout weixinLayout;
        @BindView(R.id.staus3)
        ImageView staus3;
        @BindView(R.id.ye_layout)
        RelativeLayout yeLayout;
        @BindView(R.id.juanRel)
        RelativeLayout juanRel;
        @BindView(R.id.zaixian_rad)
        LinearLayout zaixianRad;
        @BindView(R.id.douAmount)
        ViewAmount viewAmount;
        @BindView(R.id.douText)
        TextView douText;
        @BindView(R.id.juanText)
        TextView juanText;
        @BindView(R.id.beiZhu)
        EditText beiZhu;

        FootHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
