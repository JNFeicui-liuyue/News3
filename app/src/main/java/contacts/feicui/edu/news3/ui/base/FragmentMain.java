package contacts.feicui.edu.news3.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.model.volley.Response;
import contacts.feicui.edu.news3.ui.adapter.NewsAdapter;
import contacts.feicui.edu.news3.ui.adapter.NewsTypeAdapter;
import contacts.feicui.edu.news3.view.HorizontalListView;
import contacts.feicui.edu.news3.view.xlistview.XListView;

/**新闻列表界面
 * Created by liuyue on 2016/6/1.
 */
public class FragmentMain extends Fragment{
    //填充view
    private View view;
    //新闻列表
    private XListView listView;
    //新闻适配器
    private NewsAdapter newsAdapter;
    //分类列表
    private HorizontalListView hl_type;
    //分类适配器
    private NewsTypeAdapter typeAdapter;
    //新闻分类编号 默认为1
    private int subId = 1;
    //模式 1上拉 2下拉
    private int mode;
    //数据库
//    private NewsDBManager dbManager;
    //当前Activity
    private ActivityMain mainActivity;
    //更多分类
    private ImageView btn_moretype;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newslist,container,false);
        mainActivity = (ActivityMain)getActivity();
        listView= (XListView) view.findViewById(R.id.listview);
        //加载新闻分类
        if(listView != null) {
            newsAdapter = new NewsAdapter(getActivity(), listView);
            listView.setAdapter(newsAdapter);
        }
        return view;
    }

    class VolleyTypeReponseHandler implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
//            List<SubType> types = ParserNews
        }
    }
}
