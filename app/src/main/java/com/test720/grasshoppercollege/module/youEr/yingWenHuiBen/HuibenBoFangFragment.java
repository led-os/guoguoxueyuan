package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;

import org.greenrobot.eventbus.EventBus;

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
 * Created by 水东流 on 2018/8/16.
 */

public class HuibenBoFangFragment extends BaseFragment {

    String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void initData() {

    }


    @Override
    public void onStart() {
        super.onStart();
        if (url != null) {
            Glide.with(getActivity()).load(url).into(img);
        }
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.hui_ben_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @BindView(R.id.img)
    ImageView img;


    @OnClick(R.id.img)
    public void onViewClicked() {
        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.HUIBENCLICKIMG, ""));
    }
}
