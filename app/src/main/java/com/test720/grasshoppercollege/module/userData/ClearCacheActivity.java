package com.test720.grasshoppercollege.module.userData;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.greendao.gen.ZipFileDataDao;
import com.greendao.gen.bean.ZipFileData;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.RingView;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.FileSizeUntil;
import com.test720.grasshoppercollege.untils.WidgetController;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClearCacheActivity extends BaseToolActivity {


    @BindView(R.id.ringView)
    RingView ringView;
    @BindView(R.id.img)
    TextView img;
    @BindView(R.id.file)
    TextView file;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    List<Integer> colorList = new ArrayList<>();
    List<Float> rateList = new ArrayList<>();
    double fileSize;
    double imgSize;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    ZipFileDataDao zipFileDataDao;
    List<ZipFileData> list = new ArrayList<>();//zip文件列表
    MyBaseRecyclerAdapter adapter;
    TextDialog textDialog;

    class MyRun implements Runnable {
        @Override
        public void run() {
            String f = decimalFormat.format(fileSize) + "MB";
            String m = decimalFormat.format(imgSize) + "MB";
            file.setText(f);
            img.setText(m);
            // 添加的是颜色
            colorList.clear();
            colorList.add(R.color.green);
            colorList.add(R.color.red);

            float fr = (float) (fileSize / (fileSize + imgSize));
            String frs = decimalFormat.format(fr);
            float frsd = Float.parseFloat(frs) * 100;
            //  添加的是百分比
            rateList.clear();
            rateList.add(frsd);
            rateList.add(100 - frsd);
            ringView.setShow(colorList, rateList, false, true);
            WidgetController.setLayoutX(ringView, -220);
            if (adapter != null) adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected String setTitle() {
        return "清理缓存";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_clear_cache;
    }

    @Override
    protected void initData() {
        zipFileDataDao = MyApplication.getmInstance().getManage().getZipFileDataDao();
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        adapter = new MyBaseRecyclerAdapter<ZipFileData>(list, mcontext, R.layout.clear_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final ZipFileData item, final int postion) {
                holder.setText(R.id.moudle, item.getMoudle());
                holder.setText(R.id.name, item.getFileName());
                holder.setText(R.id.size, String.valueOf(item.getFileSize()) + "MB");
                holder.getView(R.id.dele).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextDialog textDialog = new TextDialog();
                        textDialog.setTextStr("是否删除？");
                        textDialog.setOkClick(new TextDialog.OkClick() {
                            @Override
                            public void click() {
                                ZipUntil zipUntil = new ZipUntil();
                                zipUntil.deleZipFile(item.getZipName(), mcontext);
                                ShowDialong();
                                mainHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DismissDialong();
                                        list.remove(postion);
                                        notifyDataSetChanged();
                                    }
                                }, 2000);
                            }

                            @Override
                            public void no() {

                            }
                        });
                        textDialog.show(getSupportFragmentManager(), "");
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    private void getData() {
        //文件大小
        list.clear();
        list = zipFileDataDao.loadAll();
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        fileSize = fileSize + list.get(i).getFileSize();
                    }
                    if (fileSize == 0) fileSize = 0.01;
                    //图片大小
                    imgSize = FileSizeUntil.getInstance().getFileOrFilesSize(mcontext.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR
                            , FileSizeUntil.SIZETYPE_MB);
                    if (imgSize == 0) imgSize = 0.01;
                    mainHandler.postDelayed(new MyRun(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            }
        }).start();
    }

    @OnClick(R.id.dele)
    public void onViewClicked() {
        if (textDialog == null) {
            textDialog = new TextDialog();
            textDialog.setTextStr("是否删除？");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    FileSizeUntil.getInstance().clearImageAllCache(mcontext);
                    ShowDialong();
                    mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DismissDialong();
                            getData();
                        }
                    }, 2000);
                }

                @Override
                public void no() {

                }
            });
        }
        textDialog.show(getSupportFragmentManager(), "img");
    }

}





