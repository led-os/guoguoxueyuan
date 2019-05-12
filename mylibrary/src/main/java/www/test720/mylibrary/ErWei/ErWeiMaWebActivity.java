package www.test720.mylibrary.ErWei;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import www.test720.mylibrary.R;

public class ErWeiMaWebActivity extends Activity {
    LinearLayout root;
    String url = "";
    protected AgentWeb mAgentWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma_web);
        root = findViewById(R.id.root);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getStringExtra("path") != null) {
            url = getIntent().getStringExtra("path");
        }

        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(root, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(url);
    }


    @Override
    protected void onPause() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mAgentWeb != null) mAgentWeb.destroy();
        mAgentWeb = null;
    }

}
