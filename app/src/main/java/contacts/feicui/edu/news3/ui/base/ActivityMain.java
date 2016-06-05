package contacts.feicui.edu.news3.ui.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.model.entity.NewsBean;
import contacts.feicui.edu.news3.ui.adapter.NewsAdapter1;
import contacts.feicui.edu.news3.view.slidingmenu.SlidingMenu;

/*主界面*/
public class ActivityMain extends MyBaseActivity {
    private static final String TAG = "ActivityMain";
    private Fragment fragmentMenu, fragmentMenuRight;
    private Fragment fragmentType, fragmentMain, fragmentLogin, fragmentRegister, fragmentFavorite, fragmentForgetPass;
    public static SlidingMenu sSlidingMenu;
    private ImageView iv_set, iv_user;
    private TextView textView_title;

    private ListView mListView;
    private static String URL = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20160603&cnt=20";

    //将URL对应的json格式对象转化为我们所封装的newsBean对象
    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
        try {
            //此句功能与url.openConnection（）.getInputStream相同。可根据URL直接联网获取网络数据，返回类型为InputStream
            String jsonString = readStream(new URL(url).openStream());
            Log.d("xys", jsonString);
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    //jsonArray中每一个都是jsonObject
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.icon = jsonObject.getString("icon");
                    newsBean.title = jsonObject.getString("title");
                    Log.d("xys", newsBean.title);
                    newsBean.summary = jsonObject.getString("summary");
                    Log.d("xys", newsBean.summary);
                    newsBean.stamp = jsonObject.getString("stamp");
                    Log.d("xys", newsBean.stamp);

                    newsBeanList.add(newsBean);
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

    //实现网络的异步访问
    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJsonData(params[0]);//请求的网址
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBean) {
            super.onPostExecute(newsBean);
            NewsAdapter1 adapter = new NewsAdapter1(ActivityMain.this, newsBean,mListView);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_title = (TextView) findViewById(R.id.textView1);
        iv_set = (ImageView) findViewById(R.id.imageView_set);
        iv_user = (ImageView) findViewById(R.id.imageView_user);
        iv_set.setOnClickListener(onClickListener);
        iv_user.setOnClickListener(onClickListener);

        mListView = (ListView) findViewById(R.id.lv_list);
        new NewsAsyncTask().execute(URL);

        initSlidingMenu();
//        showFragmentMain();


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_set:
                    if (sSlidingMenu != null && sSlidingMenu.isMenuShowing()) {
                        sSlidingMenu.showContent();
                    } else if (sSlidingMenu != null) {
                        sSlidingMenu.showMenu();
                    }
                    break;
                case R.id.imageView_user:
                    if (sSlidingMenu != null && sSlidingMenu.isMenuShowing()) {
                        sSlidingMenu.showContent();
                    } else if (sSlidingMenu != null) {
                        sSlidingMenu.showSecondaryMenu();
                    }
                    break;
            }

        }
    };

    /*初始化侧滑菜单*/
    public void initSlidingMenu() {
        fragmentMenu = new FragmentMenu();
        fragmentMenuRight = new FragmentMenuRight();

        //配置SlidingMenu
        sSlidingMenu = new SlidingMenu(this);
        //设置为左右两边菜单栏
        sSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置全屏范围都可以打开菜单栏
        sSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置菜单栏的宽度
        sSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置菜单栏与类的关联：当前类显示的为菜单栏的中间界面
        sSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置左菜单栏样式
        sSlidingMenu.setMenu(R.layout.layout_menu);
        //设置右菜单栏样式
        sSlidingMenu.setSecondaryMenu(R.layout.layout_menu_right);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu, fragmentMenu).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu_right, fragmentMenuRight).commit();

    }

    //显示:“显示收藏新闻列表的Fragment”
//    public void showFragmentFavorite(){
//        setTitle("收藏新闻");
//        sSlidingMenu.showContent();
//        if(fragmentFavorite==null)
//            fragmentFavorite=new FragmentFavorite();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.layout_content, fragmentFavorite).commit();
//    }

//    //显示：“显示新闻更多分类Fragment”
//    public void showFragmentType(){
//        setTitle("分类");
//        sSlidingMenu.showContent();
//        if (fragmentType == null)
//            fragmentType = new FragmentType();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.layout_content,fragmentType).commit();
//
//    }
//
    //显示：“显示新闻列表的Fragment”
//    public void showFragmentMain(){
//        setTitle("资讯");
//        sSlidingMenu.showContent();
//        if (fragmentMain == null)
//            fragmentMain = new FragmentMain();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.layout_content,fragmentMain).commit();
//    }
//
//    //显示：“登录的Fragment”
//    public void showFragmentLogin(){
//
//    }
//
//    //显示：“注册的Fragment”
//    public void showFragmentRegister(){
//
//    }
//
//    //显示：“忘记密码的Fragment”
//    public void showFragmentForgetPass(){
//
//    }
}
