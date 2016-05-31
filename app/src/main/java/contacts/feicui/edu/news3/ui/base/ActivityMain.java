package contacts.feicui.edu.news3.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.view.slidingmenu.SlidingMenu;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //配置SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        //设置为左右两边菜单栏
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置全屏范围都可以打开菜单栏
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置菜单栏的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置菜单栏与类的关联：当前类显示的为菜单栏的中间界面
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置左菜单栏样式
        menu.setMenu(R.layout.layout_menu);
        //设置右菜单栏样式
        menu.setSecondaryMenu(R.layout.layout_menu_right);
    }
}
