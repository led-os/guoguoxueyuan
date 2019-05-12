package com.test720.grasshoppercollege.module.diandu.downDianDu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.androidkun.breakpoints.event.DownloadData;
import com.androidkun.breakpoints.event.EventMessage;
import com.androidkun.breakpoints.services.DownloadService;
import com.google.gson.Gson;
import com.greendao.gen.bean.DownBeanData;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class DownActivity extends BaseToolActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    DownAdapter adapter;
    String bookId = "";
    List<DownBeanData> list = new ArrayList<>();
    BookDataBean bookDataBean;
    ZipDialog zipDialog = new ZipDialog();
    /**
     * 文件保存路径
     */
    public final static String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/downloads/";

    @Override
    protected String setTitle() {
        return "下载";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_down;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        adapter = new DownAdapter(list, mcontext, R.layout.down_item);
        recyclerView.setAdapter(adapter);
        bookId = getIntent().getStringExtra("book_id");
        HttpParams httpParams = new HttpParams();
        httpParams.put("book_id", bookId);
        postResponse(HttpUntil.GetIntent().ReadingbookContent(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    bookDataBean = new Gson().fromJson(str, BookDataBean.class);
                    //下载压缩包
                    String zipPath = bookDataBean.getData().getBook().getZip_path();
                    DownBeanData downBeanData = new DownBeanData();
                    downBeanData.setUrl(zipPath);
                    downBeanData.setZipName(bookDataBean.getData().getBook().getAlias());
                    String fileName = bookDataBean.getData().getBook().getBook_version() +
                            bookDataBean.getData().getBook().getClassX() +
                            bookDataBean.getData().getBook().getStatus();
                    downBeanData.setSavePath(downLoadPath);
                    downBeanData.setFileName(fileName);
                    list.add(downBeanData);
                    adapter.notifyDataSetChanged();
                }
                break;
            case 10000:
                if (codeIsOne(str)) {
                    zipDialog.dismissAllowingStateLoss();
                    setResult(1, new Intent());
                    finish();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        switch (eventMessage.getType()) {
            case EventMessage.TYPE_START://开始下载
                DownloadData start = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载");
                adapter.refreshState(start.getUrl(), start.getLength(), "下载中");
                break;
            case EventMessage.TYPE_PROGRESS://下载
                DownloadData progress = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载" + progress.getProgress());
                adapter.refreshState(progress.getUrl(), progress.getLength(), "下载中");
                adapter.updateProgress(progress.getUrl(), progress.getProgress());
                break;
            case EventMessage.TYPE_FINISHED://下载完成
                final DownloadData finished = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载完成");
                adapter.refreshState(finished.getUrl(), finished.getLength(), "解压中，请稍等");
                //解压中
                zipDialog.show(getSupportFragmentManager(), "zip");
                adapter.updateProgress(finished.getUrl(), 99);
                //下载完成后调用解压，解压到本地file目录
                ZipUntil zipUntil = new ZipUntil(list.get(0).getFileName(), bookDataBean.getData().getBook().getVersion_number(), "英语点读");
                File zipFile = new File(finished.getFilePath());
                zipUntil.upZipFile(zipFile, mcontext, new ZipUntil.ZipOkEnvent() {
                    @Override
                    public void zipOkEnvent() {
                        //插入书本数据
                        BookDataInsterUntil downUntil = new BookDataInsterUntil();
                        int i = downUntil.insert(mcontext, bookDataBean);
                        FormBody.Builder builder = new FormBody.Builder();
                        builder.add("uid", MyApplication.getmInstance().getUid());
                        builder.add("book_id", bookId);
                        Post(HttpUntil.GetIntent().ReadingaddBook(), builder, 10000, false);
                    }
                });
                //停止之前的下载服务
                Intent intent1 = new Intent(this,
                        DownloadService.class);
                stopService(intent1);
                break;
            case EventMessage.TYPE_ERROR://下载失败
                DownloadData error = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载失败" + error.getMsg());
                ShowToast(error.getMsg());
                //停止之前的下载服务
                Intent intent = new Intent(this,
                        DownloadService.class);
                stopService(intent);
                break;
        }
    }
}
