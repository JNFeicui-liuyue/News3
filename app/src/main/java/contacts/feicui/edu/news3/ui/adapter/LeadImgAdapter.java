package contacts.feicui.edu.news3.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liuyue on 2016/5/30.
 */
public class LeadImgAdapter extends PagerAdapter {

    private List<View> mList;

    //设置其构造方法，接收从外界传入的页卡样式的集合
    public LeadImgAdapter(List<View> list) {
        mList = list;
    }

    //设置控件中显示界面的数量
    @Override
    public int getCount() {
        return mList.size();
    }

    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    //销毁position位置的界面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    //初始化position位置的界面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mList.get(position);
        container.addView(view);
        return view;
    }
}
