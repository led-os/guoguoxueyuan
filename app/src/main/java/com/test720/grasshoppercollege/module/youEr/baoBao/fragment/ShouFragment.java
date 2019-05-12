package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBao.BaoBaoDianDuActivity;
import com.test720.grasshoppercollege.module.youEr.baoBao.BaoBaolianXiActivity;
import com.test720.grasshoppercollege.module.youEr.baoBao.YouErBaoBaoShouActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class ShouFragment extends BaseFragment {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.one)
    ImageView one;
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.suo)
    RoundedImageView suo;

    int index = 0;
    ShouData.DataBean dataBean;

    public ShouData.DataBean getDataBean() {
        return dataBean;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public void initData() {
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        HttpParams builder = new HttpParams();
        builder.put("type", YouErBaoBaoShouActivity.type + "");
        builder.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().ChildBabyindex(), builder, 1, false);
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            getData();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.shou_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 123:
                if (codeIsOne(s)) {
                    getData();
                }
                break;
            case 1:
                ShouData data = new Gson().fromJson(s, ShouData.class);
                dataBean = data.getData().get(index);
                if (dataBean.getOpen_up().equals("1")) {
                    suo.setVisibility(View.GONE);
                } else {
                    suo.setVisibility(View.VISIBLE);
                }
                Glide.with(getActivity()).load(data.getData().get(index).getPic()).into(img);
                break;
        }
    }


    @OnClick({R.id.one, R.id.two, R.id.suo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.suo:
            case R.id.one:
                event("one");
                break;
            case R.id.two:
                event("two");
                break;
        }
    }

    private void event(String str) {
        if (getDataBean() == null) return;
        String[] strs = new String[]{getDataBean().getTitle(), getDataBean().getContent(), HttpUntil.GetIntent().getShare() + (YouErBaoBaoShouActivity.type + 10)};
        boolean can = QuanXianUntil.GetIntent(getActivity()).isCanSee(getDataBean().getPermissions(), getDataBean().getPay(),
                getDataBean().getOpen_up(), getDataBean().getPoints(), strs, new QuanXianUntil.QuanXianClick() {
                    @Override
                    public void QuanbuyCurr() {
                        Intent intent = new Intent(mContext, BuyClassActivity.class);
                        intent.putExtra("curr_type", 47);
                        intent.putExtra("type", "4");
                        jumpToActivity(intent, false);
                    }

                    @Override
                    public void QuanbuyGuoGuo() {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("uid", MyApplication.getmInstance().getUid());
                        httpParams.put("curr_type", YouErBaoBaoShouActivity.type);
                        httpParams.put("type", 2);
                        httpParams.put("points", getDataBean().getPoints());
                        httpParams.put("re_id", getDataBean().getBaby_id());
                        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
                    }

                    @Override
                    public void QuanshareCurr() {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("uid", MyApplication.getmInstance().getUid());
                        httpParams.put("curr_type", YouErBaoBaoShouActivity.type);
                        httpParams.put("type", 1);
                        httpParams.put("re_id", getDataBean().getBaby_id());
                        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
                    }
                });

        switch (str) {
            case "one":
                if (can) {
                    Intent intent = new Intent(getActivity(), BaoBaoDianDuActivity.class);
                    intent.putExtra("baby_id", getDataBean().getBaby_id());
                    intent.putExtra("type", YouErBaoBaoShouActivity.type);
                    jumpToActivity(intent, false);
                    toBig(one);
                }
                break;
            case "two":
                if (can) {
                    Intent intent1 = new Intent(getActivity(), BaoBaolianXiActivity.class);
                    intent1.putExtra("baby_id", getDataBean().getBaby_id());
                    intent1.putExtra("type", YouErBaoBaoShouActivity.type);
                    jumpToActivity(intent1, false);
                    toBig(two);
                }
                break;
        }
    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight() / 2);   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.4f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.4f, 1f);
        animatorSet.setDuration(1000);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }
}
