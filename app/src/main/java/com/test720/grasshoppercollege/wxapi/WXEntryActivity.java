package com.test720.grasshoppercollege.wxapi;

import android.content.Intent;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.untils.QuanXianUntil;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends WXCallbackActivity {
    private String result = "";


    @Override
    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                result = "分享成功";
                EventBus.getDefault().post(new CurrencyEvent("分享成功", CurrencyEvent.SHAREOK));
                if (QuanXianUntil.getQuanXianUntil().getQuanXianClick() != null)
                    QuanXianUntil.getQuanXianUntil().getQuanXianClick().QuanshareCurr();
                break;
            case -2:
                EventBus.getDefault().post(new CurrencyEvent("分享取消", CurrencyEvent.SHARE_QX));
                result = "分享取消";
                break;
            case -1:
                EventBus.getDefault().post(new CurrencyEvent("分享失败", CurrencyEvent.SHARE_SB));
                result = "分享被拒绝";
                break;
            default:
                EventBus.getDefault().post(new CurrencyEvent("分享失败", CurrencyEvent.SHARE_SB));
                result = "分享返回";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
