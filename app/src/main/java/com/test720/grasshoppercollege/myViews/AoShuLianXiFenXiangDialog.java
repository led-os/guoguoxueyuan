package com.test720.grasshoppercollege.myViews;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/26.
 */

public class AoShuLianXiFenXiangDialog extends DialogFragment {
    @BindView(R.id.doudounum)
    TextView doudounum;
    @BindView(R.id.dec)
    ImageView dec;
    @BindView(R.id.next)
    TextView next;
    Unbinder unbinder;
    String point = "";
    Share share;

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getPoint() != null) {
            doudounum.setText(getPoint());
        }
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.ao_shu_lian_xi_feng_xiang, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.dec, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dec:
                dismissAllowingStateLoss();
                break;
            case R.id.next:
                share.share();
                break;
        }
    }

    public interface Share {
        void share();
    }
}
