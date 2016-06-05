package contacts.feicui.edu.news3.common;

import android.util.Log;

/**日志管理
 * 重新对打印类的所有方法添加判别进行封装
 * Created by liuyue on 2016/5/30.
 */
public class LogUtil {

    public static final String TAG = "新闻随意看";

    //调试日志的开关
    public static boolean isDebug = true;
    public static boolean isInfo = true;
    public static boolean isWarn = true;
    public static boolean isError = true;


    public static void d(String tag,String msg){
        if (isDebug)
            Log.d(LogUtil.TAG,msg);
    }

    public static void d(String msg){
        if (isDebug)
            Log.d(LogUtil.TAG,msg);
    }

    public static void i(String tag, String msg) {
        if (isInfo)
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (isInfo)
            Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (isWarn)
            Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (isWarn)
            Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (isError)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (isError)
            Log.e(tag, msg, t);
    }
}
