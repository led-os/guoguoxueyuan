package com.test720.grasshoppercollege.module.gongLue.student.keChenBiao;

import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class KeChenJiLuActivity extends BaseToolActivity {


    @BindView(R.id.pic)
    RoundedImageView pic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected String setTitle() {
        return "课程记录";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ke_chen_ji_lu;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("name") != null)
            name.setText(getIntent().getStringExtra("name"));
        if (getIntent().getStringExtra("title") != null)
            title.setText(getIntent().getStringExtra("title"));
        if (getIntent().getStringExtra("time") != null)
            time.setText(getIntent().getStringExtra("time"));
        if (getIntent().getStringExtra("head") != null)
            GlideUntil.getInstance().headByUrl(mcontext, pic, getIntent().getStringExtra("head"));


        HttpParams httpParams = new HttpParams();
        httpParams.put("class_id", getIntent().getStringExtra("class_id"));
        postResponse(HttpUntil.GetIntent().StrategyclassRecord(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                content.setText(jsonObject.getJSONObject("data").getString("comment"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
