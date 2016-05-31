package contacts.feicui.edu.news3.common;

/**
 * Created by liuyue on 2016/5/30.
 */
public class CommonUtil {

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
