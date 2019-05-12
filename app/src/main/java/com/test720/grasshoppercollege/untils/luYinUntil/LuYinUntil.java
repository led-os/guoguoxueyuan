package com.test720.grasshoppercollege.untils.luYinUntil;

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
 * 作者：水东流 编于 2018/8/27
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

/**
 * 录音工具
 */
public class LuYinUntil {
    private Context activity;
    /*录音相关*/
    private ImageView mImageView;//根据声音分贝展示的弹框上的img
    private TextView mTextView;//录音时长（弹框中）
    private PopupWindowFactory mPop;//录音弹框
    private AudioRecoderUtils mAudioRecoderUtils;//录音工具
    static final int VOICE_REQUEST_CODE = 66;


    /**
     * 录音状态
     * i=1...正在录音，i=2。。录音结束，，，，i=0，未录音
     */
    public interface LuYinStatus {
        void status(int i);
    }

    LuYinStatus luYinStatus;

    public void setLuYinStatus(LuYinStatus luYinStatus) {
        this.luYinStatus = luYinStatus;
    }

    public interface LuYinInterface {
        View root();//录音弹框位置定位

        View changAnView();//常按录音的view

        void filePath(String path);//录制好后的输出路径

        TextView shuChuTime();//录制时间的外在展示viwe，可不设置
    }

    LuYinInterface luYinInterface;

    public LuYinUntil(Activity act, LuYinInterface face) {

        this.activity = act.getApplicationContext();
        this.luYinInterface = face;

        AndPermission.with(activity).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                View view = View.inflate(activity, R.layout.layout_microphone, null);
                mPop = new PopupWindowFactory(activity, view);
                //PopupWindow布局文件里面的控件
                mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
                mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
                mAudioRecoderUtils = new AudioRecoderUtils();
                //录音回调
                mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
                    //录音中....db为声音分贝，time为录音时长
                    @Override
                    public void onUpdate(double db, long time) {
                        if (luYinStatus != null) luYinStatus.status(1);
                        mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                        mTextView.setText(TimeUtils.long2String(time));
                        if (luYinInterface.shuChuTime() != null)
                            luYinInterface.shuChuTime().setText(TimeUtils.long2String(time));

                        //如果录音弹框已经隐藏，则结束录音，避免未捕捉到抬手动作的情况
                        if (mPop != null && !mPop.isShow()) {
                            if (luYinStatus != null) luYinStatus.status(2);
                            mAudioRecoderUtils.stopRecord();
                            mPop.dismiss();
                        }
                    }

                    //录音结束，filePath为保存路径
                    @Override
                    public void onStop(String filePath) {
                        if (luYinStatus != null) luYinStatus.status(2);
                        luYinInterface.filePath(filePath);
                        mTextView.setText(TimeUtils.long2String(0));
                    }
                });
                StartListener();
            }


            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();

        //6.0以上需要权限申请
//        requestPermissions();
    }

    int time = 0;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 100);
            time = time + 100;
            if (time > 700) {
                mPop.showAtLocation(luYinInterface.root(), Gravity.CENTER, 0, 0);
                mAudioRecoderUtils.startRecord();
                if (startListener != null) startListener.start();
                handler.removeCallbacks(runnable);
            }
        }
    };

    /*常按监听*/
    public void StartListener() {
        if (luYinInterface.changAnView() != null) {
            //Button的touch监听
            luYinInterface.changAnView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            LogUtil.logError("event按下");
                            handler.postDelayed(runnable, 100);
                            break;
                        case MotionEvent.ACTION_UP:
                            LogUtil.logError("event抬手");
                            time = 0;
                            if (startListener != null) startListener.end();
                            if (mAudioRecoderUtils != null && mPop != null) {
                                mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
                                mPop.dismiss();
                            }
                            break;
                    }
                    return true;
                }
            });
        }
    }

    /**
     * 开启扫描之前判断权限是否打开
     */
    private void requestPermissions() {
       /* //判断是否开启摄像头权限
        if ((ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
                ) {
            StartListener();
            //判断是否开启语音权限
        } else {
            //请求获取摄像头权限
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
        }*/

    }

    /**
     * 监听
     */
    StartListener startListener;

    public void setStartListener(StartListener startListener) {
        this.startListener = startListener;
    }

    public interface StartListener {
        void start();

        void end();
    }
}
