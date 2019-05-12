package com.test720.grasshoppercollege.module.guoguoDou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.bean.ShangPXQData;
import com.test720.grasshoppercollege.myViews.ViewAmount;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
 * 作者：水东流 编于 2018/10/18
 */
public class GuiGeDialog extends BaseOkDialogFragment {

    TagAdapter tagAdapter;
    private List<ShangPXQData.DataBean.GoodsBean.SpecBean> list = new ArrayList<>();
    int index = 0;
    String gid = "";

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
        return R.layout.gou_mai_pop;
    }

    @Override
    public void start() {
        updataUi();
        buy.setText("兑换");
        tagAdapter = new TagAdapter<ShangPXQData.DataBean.GoodsBean.SpecBean>(list) {
            @Override
            public View getView(FlowLayout flowLayout, int i, ShangPXQData.DataBean.GoodsBean.SpecBean item) {
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
//                    price.setText(z);
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
        title.setText(list.get(index).getValue());
        amount.setGoods_storage(Integer.parseInt(list.get(index).getStore_count()));
        GlideUntil.getInstance().imgUrl(mContext, pic, list.get(index).getPic());
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.back, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                dismissAllowingStateLoss();
                break;
            case R.id.buy:
                if (amount.getAmount() == 0) {
                    ShowToast("数量不可为0");
                    return;
                }
                if (list.size() == 0) {
                    ShowToast("暂无商品");
                    return;
                }
                Intent intent = new Intent(mContext, GuoGuoDouDuihuanActivity.class);
                intent.putExtra("gid", gid);
                intent.putExtra("value", list.get(index).getValue());
                intent.putExtra("count", amount.getAmount());
                startActivity(intent);
                dismissAllowingStateLoss();
                break;
        }
    }


    public void setList(List<ShangPXQData.DataBean.GoodsBean.SpecBean> list) {
        this.list = list;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setGid(String gid) {
        this.gid = gid;
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

