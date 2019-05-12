package com.test720.grasshoppercollege.module.diandu.downDianDu;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidkun.breakpoints.utils.DownloadUtils;
import com.greendao.gen.bean.DownBeanData;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

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
 * 作者：水东流 编于 2018/10/10
 */
public class DownAdapter extends MyBaseRecyclerAdapter<DownBeanData> {
    private long curTime = 0;
    List<DownBeanData> datas = new ArrayList<>();
    Context mcontext;

    public DownAdapter(List<DownBeanData> list, Context context, int itemLayoutId) {
        super(list, context, itemLayoutId);
        this.datas = list;
        this.mcontext = context;
    }

    @Override
    public void convert(final BaseRecyclerHolder holder, final DownBeanData item, int postion) {
        holder.setText(R.id.textName, item.getFileName());
        holder.setText(R.id.textState, item.getDownState());
        if (item.getDownState() != null && item.getDownState().equals("下载中")) {
            holder.getView(R.id.btnStart).setVisibility(View.GONE);
            holder.getView(R.id.btnPause).setVisibility(View.VISIBLE);
        }
        String s = item.getProgress() + "%";
        holder.setText(R.id.programTxt, s);
        ProgressBar progressBar = holder.getView(R.id.progressBar);
        progressBar.setProgress(item.getProgress());
        holder.getView(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        DownloadUtils.downLoad(mcontext, item.getUrl(), item.getSavePath(), item.getZipName(), 1);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                    }
                }).start();
            }
        });
        holder.getView(R.id.btnPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.setText(R.id.textState, "暂停");
                holder.getView(R.id.btnStart).setVisibility(View.VISIBLE);
                holder.getView(R.id.btnPause).setVisibility(View.GONE);
                DownloadUtils.pauseDownLoad(mcontext, item.getUrl());
            }
        });
    }

    /**
     * 刷新进度
     *
     * @param url
     * @param progress
     */
    public void updateProgress(String url, int progress) {
        for (DownBeanData data : datas) {
            if (data.getUrl().equals(url)) {
                data.setProgress(progress);
                if (System.currentTimeMillis() - curTime > 500) {
                    curTime = System.currentTimeMillis();
                    notifyDataSetChanged();
                }
                return;
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
        for (DownBeanData data : datas) {
            if (data.getUrl().equals(url)) {
                data.setLength(length);
                data.setDownState(state);
                notifyDataSetChanged();
                return;
            }
        }
    }
}
