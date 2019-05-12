package com.test720.grasshoppercollege.module.gongLue;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/8.
 */

public abstract class BaseKeMuShouYeActivity extends BaseToolActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.nianji)
    public TextView nianji;
    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    @BindView(R.id.tabs)
    public TabLayout tabs;
    @BindView(R.id.title)
    public TextView title;

    List<BaseRecyclerViewFragment> list = new ArrayList<>();

    public List<BaseRecyclerViewFragment> getList() {
        return list;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_wei_xue_tang;
    }

    @Override
    public void initView() {
        super.initView();
        //家长不分年级
        if (GongLueData.getInstance().getModularType() == 58) {
            nianji.setVisibility(View.INVISIBLE);
        } else {
            nianji.setVisibility(View.VISIBLE);
        }
        if (!MyApplication.getmInstance().nianji.equals("幼儿园"))
            nianji.setText(MyApplication.getmInstance().nianji);

    }

    /**
     * 获取年级
     *
     * @return 年级
     */
    public String getNianJi() {
        return nianji.getText().toString();
    }

    @OnClick({R.id.back, R.id.nianji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.nianji:
                NianJIDialog dialog = new NianJIDialog();
                dialog.setClickBack(new NianJIDialog.ClickBack() {
                    @Override
                    public void clickBack(String str) {
                        nianji.setText(str);
                        nianJiShuaXin();
                    }
                });
                dialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }

    /**
     * 刷新数据
     */
    private void nianJiShuaXin() {
        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.NIANJICHANGE, "change"));
    }


}
