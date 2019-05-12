package www.test720.mylibrary.ErWei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import www.test720.mylibrary.LogUtil;
import www.test720.mylibrary.MyLibUserData;
import www.test720.mylibrary.R;


public class ErWeiMaActivity extends Activity implements QRCodeView.Delegate {
    //public class ErWeiMaActivity extends Activity {
    ZXingView mZBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mZBarView = findViewById(R.id.zxingview);
        initView();
    }

    private void initView() {
        //隐藏toolbar上面的二维码图标
//        scanner.setVisibility(View.GONE);
//        btnPhoto.setVisibility(View.VISIBLE);
        //设置二维码的代理
        mZBarView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        mZBarView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.1秒后开始识别
    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZBarView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        String re = result + "&uid=" + MyLibUserData.getInstance().getUid();
        LogUtil.logError("result" + re);
        Intent intent = new Intent(this, ErWeiMaWebActivity.class);
        intent.putExtra("path", re);
        startActivity(intent);
        finish();
        mZBarView.startSpot(); // 延迟0.1秒后开始识别
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e("lxl", "打开相机出错");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mZBarView.showScanRect();
    }

}
