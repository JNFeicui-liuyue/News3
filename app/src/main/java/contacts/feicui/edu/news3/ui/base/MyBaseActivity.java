package contacts.feicui.edu.news3.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import contacts.feicui.edu.news3.common.LogUtil;

/**封装Activity常用的方法，减少代码重用
 * Created by liuyue on 2016/5/31.
 */
public class MyBaseActivity extends Activity{

    protected int screen_w,screen_h;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取屏幕的宽和高
        screen_w = getWindowManager().getDefaultDisplay().getWidth();
        screen_h = getWindowManager().getDefaultDisplay().getHeight();
    }

    //父类activity用来调试打印activity生命周期
    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onRestart() ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onResume() ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onPause() ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onStop() ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(LogUtil.TAG, getClass().getSimpleName() + "onDestroy() ");
    }

    /*对界面跳转方法进行封装，能够更加方便调用*/
    //通过class名字进行界面跳转
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, null);
    }

    //通过class名字进行界面跳转只带Bunble数据
    public void openActivity(Class<?> pClass, Bundle bundle) {
        openActivity(pClass, bundle, null);
    }

    //通过class名字进行界面跳转并带Bunble，Uri数据
    public void openActivity(Class<?> pClass, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);

    }

    //通过action字符串进行界面跳转
    public void openActivity(String action) {
        openActivity(action, null, null);
    }

    //通过action字符串进行界面跳转，只带Bundle数据
    public void openActivity(String action, Bundle bundle) {
        openActivity(action, bundle, null);
    }

    //通过action字符串进行界面跳转，并带Bundle和Uri数据
    public void openActivity(String action, Bundle bundle, Uri uri) {
        Intent intent = new Intent(action);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);

    }
    /*公共功能封装*/
    private Toast mToast;
    public void showToast(int resId){
        showToast(getString(resId));
    }

    public void showToast(String msg){
        if (mToast == null)
            mToast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setText(msg);
        mToast.show();
    }
}
