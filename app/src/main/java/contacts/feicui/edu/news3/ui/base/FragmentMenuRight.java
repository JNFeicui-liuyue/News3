package contacts.feicui.edu.news3.ui.base;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import contacts.feicui.edu.news3.R;

/**右边侧拉界面
 * Created by liuyue on 2016/5/31.
 */
public class FragmentMenuRight extends Fragment {
    private View mView;
    private RelativeLayout relativeLayout_unlogin;
    private RelativeLayout relativeLayout_logined;
    private SharedPreferences mSharedPreferences;
    boolean islogin;

    //分享到微信
    private ImageView iv_friend;
    //分享到QQ
    private ImageView iv_qq;
    //分享到朋友圈
    private ImageView iv_friends;
    //分享到微博
    private ImageView iv_weibo;
    //分享位置规定
    public static final int WEBCHAT = 1,QQ = 2,WEBCHATMOMENTS = 3,SINA = 4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu_right,container,false);
        mSharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        islogin = mSharedPreferences.getBoolean("islogin",false);
        relativeLayout_unlogin = (RelativeLayout) mView.findViewById(R.id.relativelayout_unlogin);
        relativeLayout_logined = (RelativeLayout) mView.findViewById(R.id.relativelayout_logined);

        return mView;
    }
}
