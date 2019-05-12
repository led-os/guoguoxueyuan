package com.test720.grasshoppercollege.untils.isUntil;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

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
 * 作者：水东流 编于 2018/11/1
 */
public class ISUntil {

    private static final ISUntil ourInstance = new ISUntil();

    public static ISUntil getInstance() {
        return ourInstance;
    }

    private ISUntil() {
        ISNav.getInstance().init(imageLoader);//初始化图片加载方式
    }

    ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            GlideUntil.getInstance().imgUrl(context, imageView, path);
        }
    };

    public void start(final Context mcontext, final int num, final int type) {
        start(mcontext, num, type, true);
    }

    public void start(final Context mcontext, final int num, final int type, final boolean b) {
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (requestCode == 100) {//权限申请成功
                            // 配置选项
                            ISListConfig config = new ISListConfig.Builder()
                                    // 是否多选
                                    .multiSelect(false)
                                    //是否默认记住上次选择照片
                                    .rememberSelected(false)
                                    .btnText("确定")
                                    // 确定按钮背景色
                                    //.btnBgColor(Color.parseColor(""))
                                    // 确定按钮文字颜色
                                    .btnTextColor(Color.WHITE)
                                    // 使用沉浸式状态栏
                                    .statusBarColor(Color.parseColor("#3F51B5"))
                                    // 返回图标ResId
//                                    .backResId(R.mipmap.icon_app)
                                    .title(mcontext.getString(R.string.app_name))
                                    .titleColor(Color.WHITE)
                                    .titleBgColor(Color.parseColor("#3F51B5"))
                                    .allImagesText("相册类型")
                                    .needCrop(b)
                                    .cropSize(2, 3, 400, 600)
                                    // 第一个是否显示相机
                                    .needCamera(true)
                                    // 最大选择图片数量
                                    .maxNum(num)
                                    .build();
                            ISNav.getInstance().toListActivity(mcontext, config, type);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Toast.makeText(mcontext, "未能获取到权限", Toast.LENGTH_SHORT).show();
                    }
                }).start();


    }

    public void zhengjian(final Context mcontext, final int num, final int type) {
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (requestCode == 100) {//权限申请成功
                            // 配置选项
                            ISListConfig config = new ISListConfig.Builder()
                                    // 是否多选
                                    .multiSelect(false)
                                    //是否默认记住上次选择照片
                                    .rememberSelected(false)
                                    .btnText("确定")
                                    // 确定按钮背景色
                                    //.btnBgColor(Color.parseColor(""))
                                    // 确定按钮文字颜色
                                    .btnTextColor(Color.WHITE)
                                    // 使用沉浸式状态栏
                                    .statusBarColor(Color.parseColor("#3F51B5"))
                                    // 返回图标ResId
//                                    .backResId(R.mipmap.icon_app)
                                    .title(mcontext.getString(R.string.app_name))
                                    .titleColor(Color.WHITE)
                                    .titleBgColor(Color.parseColor("#3F51B5"))
                                    .allImagesText("相册类型")
                                    .needCrop(true)
                                    .cropSize(3, 2, 900, 600)
                                    // 第一个是否显示相机
                                    .needCamera(true)
                                    // 最大选择图片数量
                                    .maxNum(num)
                                    .build();
                            ISNav.getInstance().toListActivity(mcontext, config, type);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Toast.makeText(mcontext, "未能获取到权限", Toast.LENGTH_SHORT).show();
                    }
                }).start();


    }


}
