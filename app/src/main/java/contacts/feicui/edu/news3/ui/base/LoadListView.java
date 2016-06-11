package contacts.feicui.edu.news3.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import contacts.feicui.edu.news3.R;

/**
 * Created by liuyue on 2016/6/10.
 */
public class LoadListView extends LinearLayout {
    View footer;//底部布局
    ProgressBar mProgressBar;
    TextView loadMore;
    ListView mListView;

    public LoadListView(Context context, AttributeSet attrs) {

        super(context, attrs);
        footer = LayoutInflater.from(context).inflate(R.layout.xlistview_footer, null);
        mProgressBar = (ProgressBar) footer.findViewById(R.id.xlistview_footer_progressbar);
        loadMore = (TextView) footer.findViewById(R.id.xlistview_footer_hint_textview);
        setOrientation(VERTICAL);
        addView(footer, mListView.getFooterViewsCount());
    }


    //添加底部加载提示布局到ListView
    public void  initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.xlistview_footer,null);
        this.addView(footer);
    }
}
