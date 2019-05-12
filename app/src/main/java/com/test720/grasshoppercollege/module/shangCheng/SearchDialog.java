package com.test720.grasshoppercollege.module.shangCheng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
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
 * 作者：水东流 编于 2018/11/2
 */
public class SearchDialog extends BaseOkDialogFragment {
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tag)
    TagFlowLayout tag;
    private List<String> hot_search = new ArrayList<>();

    public void setHot_search(List<String> hot_search) {
        this.hot_search = hot_search;
    }

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.TOP;
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
        return R.layout.search_dialog;
    }

    @Override
    public void start() {
        TagAdapter adapter = new TagAdapter<String>(hot_search) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.re_shou_text, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(str);
                return view;
            }
        };
        tag.setAdapter(adapter);
        tag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(mContext, ShangPinListActivity.class);
                intent.putExtra("key", hot_search.get(position));
                startActivity(intent);
                dismissAllowingStateLoss();
                return true;
            }
        });
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.back, R.id.xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                dismissAllowingStateLoss();
                break;
            case R.id.xiaoxi:
                Intent intent = new Intent(mContext, ShangPinListActivity.class);
                if (!TextUtils.isEmpty(edittext.getText())) {
                    intent.putExtra("key", edittext.getText().toString());
                    startActivity(intent);
                    dismissAllowingStateLoss();
                }

                break;
        }
    }
}
