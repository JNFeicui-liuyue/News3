package contacts.feicui.edu.news3.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.model.biz.ImageLoader;
import contacts.feicui.edu.news3.model.entity.News;

/**
 * Created by liuyue on 2016/6/4.
 */
public class NewsAdapter1 extends BaseAdapter implements AbsListView.OnScrollListener{

    private List<News> mList;
    //用一个layout布局来接收转化的item
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    //listView当前可见的起始项
    private int mStart,mEnd;
    //用来保存当前我们所获取到的所有的图片url地址
    public static String[] URLS;
    //判断是否是第一次启动
    private boolean mFirstIn;


    public NewsAdapter1(Context context, List<News> data, ListView listView){
        mList = data;
        mInflater = LayoutInflater.from(context);
        //只保留一个LruCache
        mImageLoader = new ImageLoader(listView);
        URLS = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            //把data中的所有的图片的url转到了静态数组中
            URLS[i] = data.get(i).icon;
        }
        mFirstIn = true;
        //绑定滚动监听
        listView.setOnScrollListener(this);



    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            //将一个layout文件转化成一个item
            convertView = mInflater.inflate(R.layout.item_list_news,null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.tvSummary = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.tvStamp = (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(R.drawable.defaultpic);
        String url = mList.get(position).icon;
        viewHolder.ivIcon.setTag(url);
//        new ImageLoader().showImageByThread(viewHolder.ivIcon,
//               url);
        //使用Cache不能再这样实例化
//        new ImageLoader().showImageByAsyncTask(viewHolder.ivIcon,url);
        //修改后
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon,url);
        viewHolder.tvTitle.setText(mList.get(position).title);
        viewHolder.tvSummary.setText(mList.get(position).summary);
        viewHolder.tvStamp.setText(mList.get(position).stamp);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //判断当前listView的滚动状态
        if (scrollState == SCROLL_STATE_IDLE){
            //如果处于停止滚动状态就加载可见项
            mImageLoader.loadImage(mStart,mEnd);


        }else{
            //停止任务
            mImageLoader.cancelAllTasks();
        }
    }

    /*firstVisibleItem：第一个可见元素
    *visibleItemCount：可见元素的个数 */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        //listView当前可见的起始项
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;

        //第一次显示的时候调用
        if (mFirstIn && visibleItemCount > 0){
            //人为加载图片
            mImageLoader.loadImage(mStart,mEnd);
            mFirstIn = false;
        }
    }


    class ViewHolder{
        public TextView tvTitle,tvSummary,tvStamp;
        public ImageView ivIcon;
    }
}
