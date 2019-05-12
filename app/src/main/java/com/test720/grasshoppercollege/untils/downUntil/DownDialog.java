package com.test720.grasshoppercollege.untils.downUntil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.breakpoints.event.DownloadData;
import com.androidkun.breakpoints.event.EventMessage;
import com.androidkun.breakpoints.services.DownloadService;
import com.androidkun.breakpoints.utils.DownloadUtils;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
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
 * 作者：水东流 编于 2018/10/12
 */
public class DownDialog extends BaseOkDialogFragment {
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.num)
    TextView num;


    String path = "";//zip,下载地址
    String fileName = "";//文件名
    String zipName = "";//zip文件名
    String moudle = "";//模块名
    String version = "";//模块名


    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    ZipUntil.ZipOkEnvent zipOkEnvent;//zip文件解压完成监听

    public void setZipOkEnvent(ZipUntil.ZipOkEnvent zipOkEnvent) {
        this.zipOkEnvent = zipOkEnvent;
    }

    /**
     * 文件保存路径
     */
    public final static String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/downloads/";
//    public String downLoadPath;


    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
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
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_down_dialog;
    }

    @Override
    public void start() {
//        downLoadPath = HttpUntil.GetIntent().filePathHead(mContext);
        seekBar.setFocusable(false);
        seekBar.setClickable(false);
        seekBar.setSelected(false);
        title.setText(fileName);

        downStart();
        //直接开始下载

    }

    private void downStart() {
        AndPermission.with(getActivity()).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobNetInfo == null) {
                    Toast.makeText(mContext.getApplicationContext(), R.string.downExcept, Toast.LENGTH_LONG).show();
                    dismissAllowingStateLoss();
                    return;
                }
                DownloadUtils.downLoad(mContext, path, downLoadPath, zipName, 3);

            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();
    }


    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.start, R.id.end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                downStart();
                break;
            case R.id.end:
                DownloadUtils.pauseDownLoad(mContext, path);
                break;
        }
    }


    @Override
    public void onEvent(EventMessage eventMessage) {
        switch (eventMessage.getType()) {
            case EventMessage.TYPE_START://开始下载
                DownloadData start = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载");
                status.setText("开始下载");
                refreshState(start.getUrl(), start.getLength(), "下载中");
                break;

            case EventMessage.TYPE_PROGRESS://下载
                DownloadData progress = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载" + progress.getProgress());
                status.setText("下载中");
                updateProgress(progress.getUrl(), progress.getProgress());
                break;

            case EventMessage.TYPE_FINISHED://下载完成
                DownloadData finished = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载完成");
                status.setText("解压中");
//                zipDialog.show(getChildFragmentManager(), "zip");
                //下载完成后调用解压，解压到本地file目录
                LogUtil.logError("zip下载完成" + finished.getFilePath());
                File zipFile = new File(finished.getFilePath());
                ZipUntil zipUntil = new ZipUntil(fileName, "", moudle);
                zipUntil.upZipFile(zipFile, mContext, zipOkEnvent);
                //停止之前的下载服务
                Intent intent = new Intent(getContext(),
                        DownloadService.class);
                getContext().stopService(intent);
                break;

            case EventMessage.TYPE_ERROR://下载失败
                DownloadData error = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载失败" + error.getMsg());
                status.setText("下载任务已存在");
                refreshState(error.getUrl(), error.getLength(), "下载失败，请重试");
                //停止之前的下载服务
                Intent intent1 = new Intent(getContext(),
                        DownloadService.class);
                getContext().stopService(intent1);
                break;
        }
    }

    /**
     * 刷新进度
     *
     * @param url
     * @param progress
     */
    private long curTime = 0;

    public void updateProgress(String url, int progress) {
        if (System.currentTimeMillis() - curTime > 500) {
            curTime = System.currentTimeMillis();
            if (url.equals(path)) {
                seekBar.setProgress(progress);
                String s = progress + "%";
                num.setText(s);
            }
        }
    }

    /**
     * 刷新下载状态
     *
     * @param url
     * @param length
     * @param state
     */
    public void refreshState(String url, int length, String state) {
        if (path.equals(url)) {
            status.setText(state);
        }
    }

}
