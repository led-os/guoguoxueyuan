package com.test720.grasshoppercollege.untils.downUntil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidkun.breakpoints.event.DownloadData;
import com.androidkun.breakpoints.event.EventMessage;
import com.androidkun.breakpoints.utils.DownloadUtils;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.BuildConfig;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.MyFileProvider;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;

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
 * 作者：水东流 编于 2018/10/16
 */
public class DownApkDialog extends BaseOkDialogFragment {
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pro)
    TextView pro;
    @BindView(R.id.status)
    TextView status;

    String path = "";//apk,下载地址
    String fileName;//文件名

    public void setPath(String path) {
        this.path = path;
    }


    /**
     * 文件保存路径
     */
    public String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/downloads/";

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
        return R.layout.down_apk_dialog;
    }

    @Override
    public void start() {
        fileName = mContext.getResources().getString(R.string.app_name);
        title.setText(fileName);
        DownloadUtils.downLoad(mContext, path, downLoadPath, fileName, 3);
    }


    @Override
    public void getSuccess(String s, int what) {

    }


    @Override
    public void onEvent(EventMessage eventMessage) {
        switch (eventMessage.getType()) {
            case EventMessage.TYPE_START://开始下载
                DownloadData start = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载");
                status.setText("下载中");
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
                status.setText("安装中");
                //下载完成后安装
                File file = new File(finished.getFilePath());
                finishApk(file);

                break;
            case EventMessage.TYPE_ERROR://下载失败
                DownloadData error = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载失败" + error.getMsg());
                status.setText("下载失败");
                refreshState(error.getUrl(), error.getLength(), "失败");
                break;
        }
    }

    /**
     * 安装
     */

    private void finishApk(final File file) {
        AndPermission.with(this).requestCode(100).permission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    AndPermission
                            .with(DownApkDialog.this)
                            .requestCode(101)
                            .permission(Manifest.permission.REQUEST_INSTALL_PACKAGES)
                            .callback(new PermissionListener() {
                                @Override
                                public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                    anzhuangApk();
                                }

                                @Override
                                public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                                }
                            });
                } else {
                    anzhuangApk();
                }


            }

            /**
             * 安装apk
             */
            private void anzhuangApk() {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 以新压入栈
                if (Build.VERSION.SDK_INT >= 24) {//7.0手机适配
                    LogUtil.logError("build" + BuildConfig.APPLICATION_ID);
                    Uri apkUri = MyFileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".FileProvider", file);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                } else {
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setDataAndType(Uri.fromFile(file.getAbsoluteFile()),
                            "application/vnd.android.package-archive");
                }
                startActivity(intent);
                dismissAllowingStateLoss();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                LogUtil.logError("内存访问权限获取失败");
            }
        }).start();


    }

    /**
     * 刷新进度
     *
     * @param url  下载地址
     * @param progress  进度
     */
    private long curTime = 0;

    public void updateProgress(String url, int progress) {
        if (System.currentTimeMillis() - curTime > 500) {
            curTime = System.currentTimeMillis();
            if (url.equals(path)) {
                seekBar.setProgress(progress);
                String s = progress + "%";
                pro.setText(s);
            }
        }
    }

    /**
     * 刷新下载状态
     *
     * @param url    地址
     * @param length 大小
     * @param state  状态
     */
    public void refreshState(String url, int length, String state) {
        if (path.equals(url)) {
            status.setText(state);
        }
    }
}
