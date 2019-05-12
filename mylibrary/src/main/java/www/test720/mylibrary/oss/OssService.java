package www.test720.mylibrary.oss;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import www.test720.mylibrary.LogUtil;

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
 * 作者：水东流 编于 2018/12/16
 */
public class OssService {
    private OSS oss;
    private String mulu = "Uploads/comment_img/";
    private String accessKeyId = "LTAIxWdex9tqp3Qk";
    private String bucketName = "guoguoxueyuan";
    private String accessKeySecret = "j3No5B0u6qA9S2L32BlzRdI6j5fp5P";
    private String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    private Context context;
    private List<String> list;
    SendDialog sendDialog = new SendDialog();
    FragmentManager fragmentManager;

    public OssService(Context context, FragmentManager fm) {
        this.context = context;
        this.fragmentManager = fm;
        list = null;
        list = new ArrayList<>();
        //初始化
        initOSSClient();

    }

    public OssService(String mulu, Context context, FragmentManager fragmentManager) {
        this.mulu = mulu;
        this.context = context;
        this.fragmentManager = fragmentManager;
        list = null;
        list = new ArrayList<>();
        //初始化
        initOSSClient();
    }

    private void initOSSClient() {
        //OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider("<StsToken.AccessKeyId>", "<StsToken.SecretKeyId>", "<StsToken.SecurityToken>");
        //这个初始化安全性没有Sts安全，如需要很高安全性建议用OSSStsTokenCredentialProvider创建（上一行创建方式）多出的参数SecurityToken为临时授权参数
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        // oss为全局变量，endpoint是一个OSS区域地址
        oss = new OSSClient(context, endpoint, credentialProvider, conf);

    }

    /**
     * 上传单文件
     *
     * @param uid
     * @param path
     * @param sendBack
     */
    public void beginupload(String uid, String path, final SignSendBack sendBack) {
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        if (!mulu.equals("Uploads/android/")) {
            if (uid == null || uid.equals("")) return;
        }
        if (path == null || path.equals("")) {
            sendBack.sucess(null);
            return;
        }
        //获取当前时间戳
        long t1 = System.currentTimeMillis();
        File tempFile = new File(path);
        String fn = tempFile.getName();
        //拿到文件格式
        fn = fn.substring(fn.lastIndexOf(".") + 1);
        //上传到oss上的保存路径
        final String objectname = mulu + uid + File.separator + t1 + "." + fn;
        LogUtil.logError("上传文件名" + objectname);

        //下面3个参数依次为bucket名，Object名，上传文件路径
        PutObjectRequest put = new PutObjectRequest(bucketName, objectname, path);
        LogUtil.logError("oss" + "正在上传中....");
        //ToastUtils.showShort("正在上传中....");
        // 异步上传，可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.e("oss", "currentSize: " + currentSize + " totalSize: " + totalSize);
                double progress = currentSize * 1.0 / totalSize * 100.f;
                if (sendBack != null) {
                    sendBack.onProgressCallback(progress);
                }
            }
        });
        @SuppressWarnings("rawtypes")
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtil.logError("oss" + "UploadSuccess....");
                if (sendBack != null) {
                    sendBack.sucess(objectname);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                if (sendBack != null) sendBack.failure();
                // 请求异常
                LogUtil.logError("oss" + "请求异常....");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    LogUtil.logError("oss" + "UploadFailure：表示向OSS发送请求或解析来自OSS的响应时发生错误。\\n\" +\n" +
                            "                    " +
                            "        \"  *例如，当网络不可用时，这个异常将被抛出....");
                    LogUtil.logError("oss" + clientExcepion.getMessage());
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.logError("oss" + "UploadFailure：表示在OSS服务端发生错误");
                    LogUtil.logError("oss" + "ErrorCode" + serviceException.getErrorCode());
                    LogUtil.logError("oss" + "RequestId" + serviceException.getRequestId());
                    LogUtil.logError("oss" + "HostId" + serviceException.getHostId());
                    LogUtil.logError("oss" + "RawMessage" + serviceException.getRawMessage());
                }
            }
        });
        //task.cancel(); // 可以取消任务
        //task.waitUntilFinished(); // 可以等待直到任务完成
    }

    /**
     * 上传多文件
     *
     * @param uid
     * @param pathList
     * @param sendBack
     */
    public void beginUploads(String uid, final List<String> pathList, final SendBack sendBack) {
        if (fragmentManager != null) sendDialog.show(fragmentManager, "send");
        for (int i = 0; i < pathList.size(); i++) {
            beginupload(uid, pathList.get(i), new SignSendBack() {
                @Override
                public void sucess(String filepath) {
                    list.add(filepath);
                    if (list.size() == pathList.size()) {//说明文件集已经上传完毕
                        if (sendBack != null) sendBack.sucess(list);
                        LogUtil.logError(list.toString());
                    }
                    sendDialog.dismissAllowingStateLoss();
                }

                @Override
                public void failure() {
                    if (sendBack != null) sendBack.failure();
                    sendDialog.dismissAllowingStateLoss();
                }

                @Override
                public void onProgressCallback(double progress) {

                }
            });
        }
        //不需要上传文件时，直接回调
        if (pathList.size() == 0) {
            if (sendBack != null) sendBack.sucess(list);
        }

    }


    /**
     * 监听
     */
    public interface SendBack {
        void sucess(List<String> filepath);

        void failure();

    }


    public interface SignSendBack {
        void sucess(String filepath);

        void failure();

        void onProgressCallback(double progress);
    }
}
