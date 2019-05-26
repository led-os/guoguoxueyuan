package com.test720.grasshoppercollege.module.ciDai;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.breakpoints.event.DownloadData;
import com.androidkun.breakpoints.event.EventMessage;
import com.androidkun.breakpoints.services.DownloadService;
import com.androidkun.breakpoints.utils.DownloadUtils;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.CiDaiNeiRongData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.downDianDu.ZipDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class CiDaiDownActivity extends BaseToolActivity {
    String url = "";//下载地址
    String fileName = "";//文件名
    String version = "";//新版本号
    String bookId = "";//书id
    String fileMulu = "";//存储目录
    ZipDialog zipDialog = new ZipDialog();
    /**
     * 文件保存路径
     */
//    public String downLoadPath;

    public String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/downloads/";
    CiDaiNeiRongData ciDaiNeiRongData;//书本数据

    @Override
    protected String setTitle() {
        return "下载";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_publick_down;
    }

    @Override
    protected void initData() {
//        downLoadPath = HttpUntil.GetIntent().filePathHead(mcontext);
        if (getIntent().getStringExtra("fileName") != null)
            fileName = getIntent().getStringExtra("fileName");
        if (getIntent().getStringExtra("url") != null)
            url = getIntent().getStringExtra("url");
        if (getIntent().getStringExtra("bookId") != null)
            bookId = getIntent().getStringExtra("bookId");
        if (getIntent().getStringExtra("version") != null)
            version = getIntent().getStringExtra("version");
        if (getIntent().getStringExtra("fileMulu") != null)
            version = getIntent().getStringExtra("fileMulu");
        textName.setText(fileName);
        LogUtil.logError("下载地址" + url);
        LogUtil.logError("版本号" + version);
        LogUtil.logError("bookId" + bookId);

        //课本数据
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("bookId"));
        Post(HttpUntil.GetIntent().bookContent(), builder, 1);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        if (what == 1 && codeIsOne(str)) {
            ciDaiNeiRongData = new Gson().fromJson(str, CiDaiNeiRongData.class);
            AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
                @Override
                public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                    try {
                        DownloadUtils.downLoad(mcontext, url, downLoadPath, ciDaiNeiRongData.getData().getInfo().getAlias(), 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ShowToast("此设备不支持资源安装，请您换台手机或设备，谢谢!");
                    }


                }

                @Override
                public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                }
            }).start();
        }

        if (what == 10000) {
            if (codeIsOne(str)) {
                if (zipDialog != null) zipDialog.dismissAllowingStateLoss();
                updateProgress(100);
                textState.setText("完成");
                finish();
            }
        }
    }


    @OnClick({R.id.btnStart, R.id.btnPause})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                        if (mobNetInfo == null) {
                            Toast.makeText(mcontext.getApplicationContext(), R.string.downExcept, Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (ciDaiNeiRongData != null)
                            DownloadUtils.downLoad(mcontext, url, downLoadPath, ciDaiNeiRongData.getData().getInfo().getAlias(), 3);

                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                    }
                }).start();

                break;
            case R.id.btnPause:
                textState.setText("暂停");
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                DownloadUtils.pauseDownLoad(mcontext, url);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        switch (eventMessage.getType()) {
            case EventMessage.TYPE_START://开始下载
                DownloadData start = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载");
                textState.setText("开始下载");
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                break;
            case EventMessage.TYPE_PROGRESS://下载
                DownloadData progress = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载" + progress.getProgress());
                textState.setText("下载中");
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                updateProgress(progress.getProgress());
                break;
            case EventMessage.TYPE_FINISHED://下载完成
                final DownloadData finished = (DownloadData) eventMessage.getObject();
                LogUtil.logError("解压中，请稍等");
                //解压中
                zipDialog.show(getSupportFragmentManager(), "zip");
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                textState.setText("解压中，请稍等");
                ShowDialong();
                //下载完成后调用解压，解压到本地file目录
                ZipUntil zipUntil = new ZipUntil(fileName, version, "磁带播放");
                File zipFile = new File(finished.getFilePath());
                zipUntil.upZipFile(zipFile, mcontext, new ZipUntil.ZipOkEnvent() {
                    @Override
                    public void zipOkEnvent() {
                        if (ciDaiNeiRongData != null) {
                            //设置本地资源版本
                            BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
                            bookDataInsterUntil.saveCiDaiV(mcontext, bookId, version);
                            bookDataInsterUntil.saveCiDaiBean(mcontext, ciDaiNeiRongData);
                            FormBody.Builder builder = new FormBody.Builder();
                            builder.add("uid", MyApplication.getmInstance().getUid());
                            builder.add("book_id", bookId);
                            Post(HttpUntil.GetIntent().EnCdaddReadingPoints(), builder, 10000);
                        }
                    }
                },zipDialog);
                //停止之前的下载服务
                Intent intent = new Intent(this,
                        DownloadService.class);
                stopService(intent);
                break;
            case EventMessage.TYPE_ERROR://下载失败
                DownloadData error = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载失败" + error.getMsg());
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                textState.setText(error.getMsg());
                ShowToast(error.getMsg());
                //停止之前的下载服务
                Intent intent1 = new Intent(this,
                        DownloadService.class);
                stopService(intent1);
                break;
        }
    }

    /**
     * 刷新进度
     *
     * @param progress 进度
     */
    private long curTime = 0;

    public void updateProgress(int progress) {
        if (System.currentTimeMillis() - curTime > 500) {
            curTime = System.currentTimeMillis();
            progressBar.setProgress(progress);
            String s = progress + "%";
            programTxt.setText(s);
        }
    }


    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.programTxt)
    TextView programTxt;
    @BindView(R.id.btnStart)
    TextView btnStart;
    @BindView(R.id.btnPause)
    TextView btnPause;
}
