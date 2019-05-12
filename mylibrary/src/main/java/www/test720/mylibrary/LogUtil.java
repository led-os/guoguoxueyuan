package www.test720.mylibrary;

import android.util.Log;

/**
 *
 * Created by Administrator on 2017/10/16 0016.
 */

public class LogUtil {
    private static  int ErrorCode = 2;
    private static  int MineCode = 1;

    public static void logError(String s) {
        if (ErrorCode > MineCode)
        Log.e("lxl", s);
    }

}
