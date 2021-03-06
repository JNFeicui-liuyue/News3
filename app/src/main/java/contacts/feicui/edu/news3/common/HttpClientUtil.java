package contacts.feicui.edu.news3.common;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.net.URL;

/**网络下载公共类
 * Created by liuyue on 2016/5/31.
 */
public class HttpClientUtil {
    //网络连接对象
    private static HttpClient mHttpClient;
    //超时时间
    private static int Timeout = 5000;
    //最大连接数量
    private static int MaxTotalConnections = 8;

    //设置HTTPclient对象的超时时间，最大连接数量
    public static synchronized HttpClient getHttpClient(){
        if (mHttpClient == null){
            //建立参数对象
            HttpParams params = new BasicHttpParams();
            //设置连接池中超时时间
            ConnManagerParams.setTimeout(params,Timeout);
            ConnManagerParams.setMaxTotalConnections(params,MaxTotalConnections);
            //连接超时，这定义了通过网络与服务器建立连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params,Timeout);
            mHttpClient = new DefaultHttpClient(params);
        }
        return mHttpClient;
    }

    //连接网络，解析返回的数据
    public static String httpGet(String url){
        try {
            URL url1 = new URL("http://118.244.212.82:9092/newsClient/news_sort?ver=1&imei=1");
            HttpConnection http = (HttpConnection) url1.openConnection();

            HttpClient httpClient = getHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String resultStr = EntityUtils.toString(entity,"GBK");
            return resultStr;
        } catch (Exception e) {
           return null;
        }
    }

}
