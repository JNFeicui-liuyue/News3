package contacts.feicui.edu.news3.ui.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.view.slidingmenu.SlidingMenu;
/*主界面*/
public class ActivityMain extends MyBaseActivity {
    private Fragment fragmentMenu,fragmentMenuRight;
    private Fragment fragmentType, fragmentMain, fragmentLogin,fragmentRegister,fragmentFavorite,fragmentForgetPass;
    public static SlidingMenu sSlidingMenu;
    private ImageView iv_set,iv_user;
    private TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_title = (TextView) findViewById(R.id.textView1);
        iv_set = (ImageView) findViewById(R.id.imageView_set);
        iv_user = (ImageView) findViewById(R.id.imageView_user);
        iv_set.setOnClickListener(onClickListener);
        iv_user.setOnClickListener(onClickListener);

        initSlidingMenu();
        showFragmentMain();


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageView_set:
                    if (sSlidingMenu != null && sSlidingMenu.isMenuShowing()){
                        sSlidingMenu.showContent();
                    }else if (sSlidingMenu != null){
                        sSlidingMenu.showMenu();
                    }
                    break;
                case R.id.imageView_user:
                    if (sSlidingMenu != null && sSlidingMenu.isMenuShowing()){
                        sSlidingMenu.showContent();
                    }else if (sSlidingMenu != null){
                        sSlidingMenu.showSecondaryMenu();
                    }
                    break;
            }

        }
    };

    /*初始化侧滑菜单*/
    public void initSlidingMenu(){
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
        sSlidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置左菜单栏样式
        sSlidingMenu.setMenu(R.layout.layout_menu);
        //设置右菜单栏样式
        sSlidingMenu.setSecondaryMenu(R.layout.layout_menu_right);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu,fragmentMenu).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu_right,fragmentMenuRight).commit();

    }

    //显示:“显示收藏新闻列表的Fragment”
    public void showFragmentFavorite(){
        setTitle("收藏新闻");
        sSlidingMenu.showContent();
        if(fragmentFavorite==null)
            fragmentFavorite=new FragmentFavorite();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragmentFavorite).commit();
    }

    //显示：“显示新闻更多分类Fragment”
    public void showFragmentType(){
        setTitle("分类");
        sSlidingMenu.showContent();
        if (fragmentType == null)
            fragmentType = new FragmentType();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content,fragmentType).commit();

    }

    //显示：“显示新闻列表的Fragment”
    public void showFragmentMain(){
        setTitle("资讯");
        sSlidingMenu.showContent();
        if (fragmentMain == null)
            fragmentMain = new FragmentMain();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content,fragmentMain).commit();
    }

    //显示：“登录的Fragment”
    public void showFragmentLogin(){

    }

    //显示：“注册的Fragment”
    public void showFragmentRegister(){

    }

    //显示：“忘记密码的Fragment”
    public void showFragmentForgetPass(){

    }
}
