package com.test720.grasshoppercollege.untils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TypedValue;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import www.test720.mylibrary.IdCardUtil;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/1/18 0018.
 */

public class PublicUntil {
    private static final String TAG = "lxl";

    /*更据字符串，获取，xy*/
    public static List<Integer> StringToXY(String s) {
        List<Integer> list = new ArrayList<>();
        String[] str = s.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.parseInt(str[i]));
        }
        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void muxVideoAudio(String videoFilePath, String audioFilePath, String outputFile, long time) {
        try {
            MediaMuxer muxer = getMediaMuxer(videoFilePath, audioFilePath, outputFile, time);
            muxer.stop();
            muxer.release();
            Log.d(TAG, "Output: " + outputFile);
        } catch (IOException e) {
            Log.d(TAG, "Mixer Error 1 " + e.getMessage());
        } catch (Exception e) {
            Log.d(TAG, "Mixer Error 2 " + e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @NonNull
    private static MediaMuxer getMediaMuxer(String videoFilePath, String audioFilePath, String outputFile, long time) throws IOException {
        MediaExtractor videoExtractor = new MediaExtractor();
        videoExtractor.setDataSource(videoFilePath);
        MediaExtractor audioExtractor = new MediaExtractor();
        audioExtractor.setDataSource(audioFilePath);
        MediaMuxer muxer = new MediaMuxer(outputFile, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        videoExtractor.selectTrack(0);
        MediaFormat videoFormat = videoExtractor.getTrackFormat(0);
        int videoTrack = muxer.addTrack(videoFormat);
        audioExtractor.selectTrack(0);
        MediaFormat audioFormat = audioExtractor.getTrackFormat(0);
        int audioTrack = muxer.addTrack(audioFormat);
        Log.d(TAG, "Video Format " + videoFormat.toString());
        Log.d(TAG, "Audio Format " + audioFormat.toString());
        boolean sawEOS = false;
        int frameCount = 0;
        int offset = 100;
        int sampleSize = 256 * 1024;
        ByteBuffer videoBuf = ByteBuffer.allocate(sampleSize);
        ByteBuffer audioBuf = ByteBuffer.allocate(sampleSize);
        MediaCodec.BufferInfo videoBufferInfo = new MediaCodec.BufferInfo();
        MediaCodec.BufferInfo audioBufferInfo = new MediaCodec.BufferInfo();
        videoExtractor.seekTo(0, MediaExtractor.SEEK_TO_CLOSEST_SYNC);
        audioExtractor.seekTo(0, MediaExtractor.SEEK_TO_CLOSEST_SYNC);
        muxer.start();
        while (!sawEOS) {
            videoBufferInfo.offset = offset;
            videoBufferInfo.size = videoExtractor.readSampleData(videoBuf, offset);

            if (videoBufferInfo.size < 0 || audioBufferInfo.size < 0) {
                sawEOS = true;
                videoBufferInfo.size = 0;
            } else {
                videoBufferInfo.presentationTimeUs = videoExtractor.getSampleTime() + time;
                //noinspection WrongConstant
                videoBufferInfo.flags = videoExtractor.getSampleFlags();
                muxer.writeSampleData(videoTrack, videoBuf, videoBufferInfo);
                videoExtractor.advance();
                frameCount++;
            }
        }
        boolean sawEOS2 = false;
        int frameCount2 = 0;
        while (!sawEOS2) {
            frameCount2++;
            audioBufferInfo.offset = offset;
            audioBufferInfo.size = audioExtractor.readSampleData(audioBuf, offset);
            if (videoBufferInfo.size < 0 || audioBufferInfo.size < 0) {
                sawEOS2 = true;
                audioBufferInfo.size = 0;
            } else {
                audioBufferInfo.presentationTimeUs = audioExtractor.getSampleTime();
                //noinspection WrongConstant
                audioBufferInfo.flags = audioExtractor.getSampleFlags();
                muxer.writeSampleData(audioTrack, audioBuf, audioBufferInfo);
                audioExtractor.advance();
                frameCount2++;
            }
        }
        return muxer;
    }


    /**
     * dp转像素
     *
     * @param dp
     * @param resources
     * @return
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 获取状态通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        Log.d(TAG, "statusBarHeight:" + frame.top + "px");
        return frame.top;
    }

    /**
     * 判断是否是电话号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }

    /**
     * 比较真实完整的判断身份证号码的工具 * * @param IdCard 用户输入的身份证号码 * @return [true符合规范, false不符合规范]
     */
    public static boolean isRealIDCard(String IdCard) {
        if (IdCard != null) {
            int correct = new IdCardUtil(IdCard).isCorrect();
            if (0 == correct) {// 符合规范 return true; } } return false; }
                return true;

            }
        }
        return false;
    }

    /**
     * 检测是否安装支付宝
     *
     * @param context 界面
     * @return 是否
     */
    public static boolean checkAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    /**
     * 判断 用户是否安装微信客户端
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打乱集合顺序
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> randomList(ArrayList<T> arr) {
        Random random = new Random();
        ArrayList<T> rsult = new ArrayList<>();
        while (arr.size() > 0) {
            int randIndex = random.nextInt(arr.size());
            rsult.add(arr.remove(randIndex));
        }
        return rsult;
    }
}