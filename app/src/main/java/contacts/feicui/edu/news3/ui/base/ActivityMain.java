package contacts.feicui.edu.news3.ui.base;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.model.biz.parser.ParserNews;
import contacts.feicui.edu.news3.model.entity.News;
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
    private NewsAdapter1 adapter;
    private static String URL = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20160603&cnt=20";
    ReFlashableView      mReFlashableView;
    ListView mListView;
    ParserNews mParserNews = new ParserNews();


    //实现网络的异步访问
    class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... params) {
            return mParserNews.getJsonData();//请求的网址
        }

        @Override
        protected void onPostExecute(List<News> newsBean) {
            super.onPostExecute(newsBean);
            adapter = new NewsAdapter1(ActivityMain.this, newsBean,mListView);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //不显示ActionBar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);


        textView_title = (TextView) findViewById(R.id.textView1);
        iv_set = (ImageView) findViewById(R.id.imageView_set);
        iv_user = (ImageView) findViewById(R.id.imageView_user);
        iv_set.setOnClickListener(onClickListener);
        iv_user.setOnClickListener(onClickListener);

        mReFlashableView = (ReFlashableView) findViewById(R.id.refreshable_view);
        mListView = (ListView) findViewById(R.id.list_view);

        new NewsAsyncTask().execute(URL);

//        showFragmentMain();


        mReFlashableView.setOnRefreshListener(new ReFlashableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mReFlashableView.finishRefreshing();
            }
        }, 0);

        initSlidingMenu();

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

    /**
     * 新闻单项点击事件
     */
    private AdapterView.OnItemClickListener newsItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            //打开显示当前选中的新闻
            News news = (News) adapterView.getItemAtPosition(position);
            Intent intent = new Intent(ActivityMain.this,ActivityShow.class);
//            向Intent中放入需要携带的数据包 P231
            intent.putExtra("newsitem",news);
            startActivity(intent);

        }
    };


}
