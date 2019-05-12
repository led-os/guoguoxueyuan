package com.test720.grasshoppercollege.module.shangCheng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.bean.ShangPinXiangQingData;
import com.test720.grasshoppercollege.myViews.ViewAmount;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/12.
 */

public class DingDanTypeSetDialog extends BaseOkDialogFragment {
    int index = 0;
    String gid = "";
    TagAdapter tagAdapter;
    private List<ShangPinXiangQingData.DataBean.GoodsBean.SpecBean> list = new ArrayList<>();
    boolean isCar = false;//是否是购物车，或者是立即购买

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.shang_cheng_gou_pop;
    }


    @Override
    public void start() {
        if (list.size() > 0) {
            int store_count = Integer.parseInt(list.get(0).getStore_count());
            if (store_count > 0)
                amount.setMinNum(1);
            else {
                amount.setMinNum(0);
                amount.setAmount(0);
            }
        }
        if (isCar) {
            buy.setText("加入购物车");
        } else {
            buy.setText("立即购买");
        }
        updataUi();
        tagAdapter = new TagAdapter<ShangPinXiangQingData.DataBean.GoodsBean.SpecBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, ShangPinXiangQingData.DataBean.GoodsBean.SpecBean item) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                if (item.getValue() != null && !item.getValue().equals("")) {
                    textView.setText(item.getValue());
                    if (index == i) {
                        textView.setBackgroundResource(R.color.appColor);
                        textView.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        textView.setBackgroundResource(R.drawable.bule_bian_kuang);
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                    }
                }
                return view;
            }
        };

        tag.setAdapter(tagAdapter);

        tag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                index = i;
                tagAdapter.notifyDataChanged();
                updataUi();
                return true;
            }
        });

        //数量添加
        amount.setOnAmountChangeListener(new ViewAmount.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int pos) {
                if (list.size() != 0) {
                    float pr = Float.parseFloat(list.get(index).getPrice());
                    float zongP = pr * pos;
                    String z = "总价" + zongP;
                    price.setText(z);
                }
            }
        });

    }

    /**
     * 更新ui
     */
    private void updataUi() {
        if (list.size() == 0) return;
        kuCun.setText(list.get(index).getStore_count());
        price.setText(list.get(index).getPrice());
        vipPrice.setText(list.get(index).getVip_price());
        title.setText("请选择规格");
        amount.setGoods_storage(Integer.parseInt(list.get(index).getStore_count()));
        GlideUntil.getInstance().imgUrl(mContext, pic, list.get(index).getPic());
    }

    /*添加到购物车*/
    public void addToCar() {
        String value = list.get(index).getValue();
        if (amount.getAmount() == 0) {
            ShowToast("数量不可为0");
            return;
        }
        if (isCar) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("gid", getGid());
            httpParams.put("value", value);
            httpParams.put("count", amount.getAmount());
            post(HttpUntil.GetIntent().MalladdGoodsCart(), httpParams, 2);
        } else {
            Intent intent = new Intent(mContext, QueRenDingDanActivity.class);
            intent.putExtra("value", value);
            intent.putExtra("gid", getGid());
            intent.putExtra("count", amount.getAmount() + "");
            startActivity(intent);
            if (goToPayListener != null) goToPayListener.goToPay();
            dismissAllowingStateLoss();
        }
    }


    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 2:
                if (codeIsOne(s, true)) {
                    dismissAllowingStateLoss();
                }
                break;
        }
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    @OnClick({R.id.back, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                dismissAllowingStateLoss();
                break;
            case R.id.buy:
                addToCar();
                break;
        }
    }


    public interface GoToPayListener {
        void goToPay();
    }

    GoToPayListener goToPayListener;//跳转到支付界面监听

    public void setGoToPayListener(GoToPayListener goToPayListener) {
        this.goToPayListener = goToPayListener;
    }

    public void setList(List<ShangPinXiangQingData.DataBean.GoodsBean.SpecBean> list) {
        this.list = list;
    }

    public boolean isCar() {
        return isCar;
    }

    public void setCar(boolean car) {
        isCar = car;
    }

    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.vipPrice)
    TextView vipPrice;
    @BindView(R.id.kuCun)
    TextView kuCun;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tag)
    TagFlowLayout tag;
    @BindView(R.id.amount)
    ViewAmount amount;
    @BindView(R.id.buy)
    TextView buy;
}
