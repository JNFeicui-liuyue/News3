package contacts.feicui.edu.news3.ui.base;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import contacts.feicui.edu.news3.R;

/**Logo界面
 * Created by liuyue on 2016/5/30.
 */
public class ActivityLogo extends MyBaseActivity{

    //关联显示的布局，实例化Logo图片并加载动画
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        ImageView logo = (ImageView) findViewById(R.id.iv_logo);

        //加载动画资源
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.logo);
        //设置动画结束后保留结束状态
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            //动画启动时调用
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束时调用
            @Override
            public void onAnimationEnd(Animation animation) {

                openActivity( ActivityMain.class);
                //结束ActivityLogo
                ActivityLogo.this.finish();
            }

            //动画重复时调用
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logo.setAnimation(animation);

    }
}
