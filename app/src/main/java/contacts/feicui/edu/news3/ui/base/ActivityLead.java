package contacts.feicui.edu.news3.ui.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.ui.adapter.LeadImgAdapter;

/**引导界面
 * Created by liuyue on 2016/5/30.
 */
public class ActivityLead extends MyBaseActivity {
    private ViewPager mViewPager;
    private ImageView[] points = new ImageView[4];
    private LeadImgAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        //初始化控件
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //为ViewPager设置滑动处理事件
        mViewPager.setOnPageChangeListener(listener);

        //设置每一个具体界面的样式
        List<View> viewList = new ArrayList<View>();

        viewList.add(getLayoutInflater().inflate(R.layout.lead_1,null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_2,null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_3,null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_4,null));

        points[0] = (ImageView) findViewById(R.id.iv_p1);
        points[1] = (ImageView) findViewById(R.id.iv_p2);
        points[2] = (ImageView) findViewById(R.id.iv_p3);
        points[3] = (ImageView) findViewById(R.id.iv_p4);
        setPoint(0);

        //初始化适配器
        mAdapter = new LeadImgAdapter(viewList);
        //设置适配器
        mViewPager.setAdapter(mAdapter);
    }

    //设置滑动能够实现当滑动时改变底部小图标
    private void setPoint(int index) {
        for (int i = 0; i < points.length; i++) {
            if (i == index){
                points[i].setAlpha(255);
            }else {
                points[i].setAlpha(100);
            }
        }
    }

    //新建ViewPager对应的手势监听ViewPager.OnPagerChangeListener对象listener，重写下述方法
    //当界面切换后调用
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {


        //界面切换时调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当界面切换后调用
        @Override
        public void onPageSelected(int position) {
            setPoint(position);
            if (position >= 3){
                openActivity(ActivityLogo.class);
                finish();
                //设置首次运行才启动该界面
                //初始化SharedPreferences对象，用来保存是否是第一次打开软件的数据
                SharedPreferences preferences = getSharedPreferences("runconfig",MODE_PRIVATE);//指定该SharedPreferences数据只能被本应用程序读写
                SharedPreferences.Editor editor = preferences.edit();
                //存入指定数据
                editor.putBoolean("isFirstRun",false);
                //提交所有存入的数据
                editor.commit();
            }
        }

        //滑动状态变化时调用
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
