package contacts.feicui.edu.news3.model.biz.parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import contacts.feicui.edu.news3.model.entity.News;

/**json数据解析
 * Created by liuyue on 2016/6/3.
 */
public class ParserNews {
    private static final String TAG = "ParserNews";
    private static String URL = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20160603&cnt=20";


    //读取网页内容（通过inputStream解析网页返回的数据）
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            //从is中一行一行的读取
            String line = "";
            isr = new InputStreamReader(is, "utf-8");//字节流转化为字符流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //存放的是网页中的所有内容
        return result;
    }

    //将URL对应的json格式对象转化为我们所封装的newsBean对象
    public List<News> getJsonData() {
        List<News> newsBeanList = new ArrayList<News>();

        try {
            //此句功能与url.openConnection（）.getInputStream相同。可根据URL直接联网获取网络数据，返回类型为InputStream
            String jsonString = readStream(new URL(URL).openStream());
            Log.d("xys", jsonString);
            JSONObject jsonObject;
            News news;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    //jsonArray中每一个都是jsonObject
                    jsonObject = jsonArray.getJSONObject(i);
                    news = new News();
                    news.icon = jsonObject.getString("icon");
                    news.title = jsonObject.getString("title");
                    Log.d("xys", news.title);
                    news.summary = jsonObject.getString("summary");
                    Log.d("xys", news.summary);
                    news.stamp = jsonObject.getString("stamp");
                    Log.d("xys", news.stamp);
                    news.link = jsonObject.getString("link");
                    Log.d("xys", news.link);
                    news.nid = jsonObject.getInt("nid");
                    Log.d("xys", "nid"+news.nid);
                    news.type = jsonObject.getInt("type");
                    Log.d("xys", "type"+news.type);
                    newsBeanList.add(news);
                    Log.d(TAG, "newsBeanList size********" + newsBeanList.size());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBeanList;
    }
}
