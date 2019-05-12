package com.test720.grasshoppercollege.module.shangCheng.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.QueRenDingDanActivity;
import com.test720.grasshoppercollege.module.shangCheng.bean.CarListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/8
 */
public class GouWuCheFragment extends BaseRecyclerViewFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;
    List<CarListData.DataBean.ListBean> list = new ArrayList<>();
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.set)
    TextView set;
    @BindView(R.id.zongJia)
    TextView zongJia;
    @BindView(R.id.down_center_Lin)
    LinearLayout downCenterLin;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.all)
    CheckBox all;

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
        return HttpUntil.GetIntent().MallmyCart();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            CarListData data = new Gson().fromJson(str, CarListData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ShuaXin();
    }

    @Override
    public void gengDuo(String str) {
    }

    @Override
    public HttpParams httpParams() {
        HttpParams builder = new HttpParams();
        builder.put("uid", MyApplication.getmInstance().getUid());
        return builder;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void init() {
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                allCheck(isChecked);
            }
        });
        adapter = new MyBaseRecyclerAdapter<CarListData.DataBean.ListBean>(list, mContext, R.layout.gou_wu_che_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final CarListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.content, item.getName() + " " + item.getValue());
                holder.setImageByUrl(R.id.img, item.getCover());
                //设置数量
                holder.setText(R.id.num, item.getCount());
                holder.setText(R.id.price, "￥" + item.getPrice());
                CheckBox checkBox = holder.getView(R.id.checkbox);
                checkBox.setChecked(item.isCheck());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.setCheck(isChecked);
                        ZongJia();
                    }
                });
                holder.getView(R.id.numLin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        all.setChecked(false);
                        AmountDialog dialog = new AmountDialog();
                        dialog.setCount(Integer.parseInt(item.getCount()));
                        dialog.setMax(Integer.parseInt(item.getStore_count()));
                        dialog.setMin(1);
                        dialog.setAmountBack(new AmountDialog.AmountBack() {
                            @Override
                            public void amountNum(int num) {
                                item.setCount(num + "");
                                setCarNum(item.getCart_id(), num);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        dialog.show(getChildFragmentManager(), "num");
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.back, R.id.set, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                mContext.finish();
                break;
            case R.id.set:
                if (set.getText().toString().equals("编辑")) {
                    set.setText("完成");
                    downCenterLin.setVisibility(View.GONE);
                    right.setText("删除");
                } else {
                    set.setText("编辑");
                    downCenterLin.setVisibility(View.VISIBLE);
                    right.setText("结算");
                }
                break;
            case R.id.right:
                if (right.getText().toString().equals("删除")) {
                    deleCar();
                } else {
                    jieSuan();
                }
                break;
        }
    }


    /*全选*/
    private void allCheck(boolean check) {
        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).setCheck(check);
        }
        adapter.notifyDataSetChanged();
    }

    /*总计价格*/
    public void ZongJia() {
        float zong = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                float p = Float.parseFloat(list.get(i).getPrice());
                float num = Float.parseFloat(list.get(i).getCount());
                zong = zong + p * num;//保留2位小数
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String z = "￥" + decimalFormat.format(zong);
        zongJia.setText(z);
    }

    /*删除购物车*/
    private void delete() {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).isCheck()) {
                list.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /*修改购物车数量*/
    private void setCarNum(String cart_id, int num) {
        HttpParams builder = new HttpParams();
        builder.put("cart_id", cart_id);
        builder.put("count", num + "");
        post(HttpUntil.GetIntent().MallsetCartGoodsCount(), builder, 2, true);
    }

    /*删除购物车商品*/
    private void deleCar() {
        String cartId = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                if (cartId.equals("")) {
                    cartId = cartId + list.get(i).getCart_id();
                } else {
                    cartId = cartId + "," + list.get(i).getCart_id();
                }

            }
        }
        HttpParams builder = new HttpParams();
        builder.put("cart_id", cartId);
        builder.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().MalldelCartGoods(), builder, 3, false);
        delete();//修改本地布局
    }

    /*结算*/
    private void jieSuan() {
        String cartId = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                if (cartId.equals("")) {
                    cartId = cartId + list.get(i).getCart_id();
                } else {
                    cartId = cartId + "," + list.get(i).getCart_id();
                }

            }
        }
        if (cartId.equals("")) {
            ShowToast("请选择结算订单");
            return;
        }
        Intent inten = new Intent(mContext, QueRenDingDanActivity.class);
        inten.putExtra("cart_id", cartId);
        jumpToActivity(inten, false);
    }


    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 2) {
            if (codeIsOne(str, false)) {
                ShuaXin();
            }
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.MYORDER) {//支付成功后刷新购物车列表
            LogUtil.logError(currencyEvent.getMsg().toString());
            ShuaXin();
        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.activity_cai_shou;
    }


}
