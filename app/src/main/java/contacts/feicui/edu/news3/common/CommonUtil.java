package contacts.feicui.edu.news3.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**联网，存储数据
 * Created by liuyue on 2016/5/30.
 */
public class CommonUtil {
    public static final String APPURL = "http://118.244.212.82:9092/newsClient";

    public static final int VERSON_CODE = 1;//版本

    //获取系统的当前时间
    public static String getSystime(){
        String systime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        systime = dateFormat.format(new Date(System.currentTimeMillis()));
        return systime;
    }

    public static String getFileSize(long fileSize){
        DecimalFormat df = new DecimalFormat("#.00");//小数格式
        StringBuffer sb = new StringBuffer();
        if (fileSize < 1024){
            sb.append(fileSize);
            sb.append("B");
        }else if (fileSize < 1048576){
            sb.append(df.format((double) fileSize/1024));
            sb.append("K");
        }else if (fileSize < 1073741824){
            sb.append(df.format((double)fileSize/1048576));
            sb.append("M");
        }else {
            sb.append(df.format((double)fileSize/1073741824));
            sb.append("G");
        }
        return sb.toString();
    }

    //获取当前日期
    public static String getDate(){
        Date date = new Date(System.currentTimeMillis());
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    //验证邮箱格式
    public static boolean verifyEmail(String email){
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                        "|(([a-zA-Z0-9\\-]+\\.)+))" +
                        "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);//匹配器
        return matcher.matches();
    }

    //验证密码格式
    public static boolean verifyPassword(String password){
        Pattern pattern = Pattern
                .compile("^[a-zA-Z0-9]{6,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //获取当前的版本号
    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //联网的ip
    public static String NETIP = "118.244.212.82";
    //联网的路径
    public static String NETPATH = "http://" + NETIP + ":9092/newsClient";
    //SharedPreferences保存用户名键
    public static final String SHARE_USER_NAME = "userName";
    //SharedPreferences保存用户名密码
    public static final String SHARE_USER_PWD = "userPwd";
    //SharedPreferences保存是否第一次登陆
    public static final String SHARE_IS_FIRST_RUN = "isFirstRun";
    //SharedPreferences保存存储路径
    public static final String SHAREPATH = "news_share";
}
