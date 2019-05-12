package com.test720.grasshoppercollege.myViews;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.untils.HttpUntil;

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
 * 作者：水东流 编于 2018/8/23
 */
public class ZhaoShengYuYueDialog extends BaseOkDialogFragment {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.nianlin)
    EditText nianlin;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.nianJi)
    AutoCompleteTextView nianJi;


    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
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
                return WindowManager.LayoutParams.WRAP_CONTENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.zhao_sheng_yu_yue;
    }


    @Override
    public void start() {
        String[] autoString = new String[]{"一年级", "二年级", "三年级", "四年级", "五年级", "六年级", "幼儿园",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, autoString);
        nianJi.setAdapter(adapter);
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, true)) {
            dismissAllowingStateLoss();
        }
    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        yuYue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 预约
     */
    private void yuYue() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请留下您的名字");
            return;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请留下您的电话");
            return;
        }
        if (phone.getText().toString().length() < 11) {
            phone.setError("请留下正确的11位电话");
            return;
        }
        if (TextUtils.isEmpty(nianlin.getText())) {
            nianlin.setError("请留下您的年龄");
            return;
        }
        if (TextUtils.isEmpty(nianJi.getText())) {
            nianJi.setError("请输入年级");
            return;
        }
        Bundle bundle = getArguments();
        String currId = bundle.getString("curr_id");
        if (currId == null) currId = "";
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("name", name.getText().toString());
        httpParams.put("phone", phone.getText().toString());
        httpParams.put("age", nianlin.getText().toString());
        httpParams.put("grade", nianJi.getText().toString());
        httpParams.put("curr_id", currId);
        if (GongLueData.getInstance().getType() == GongLueData.JIGOU)
            post(HttpUntil.GetIntent().getIP() + "small.php/StrategyTrain/yuYueOrTryListening", httpParams, 1);
        else
            post(HttpUntil.GetIntent().StrategyChildyuYueOrTryListening(), httpParams, 1);
    }

}
