package contacts.feicui.edu.news3.ui.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.src.cn.sharesdk.onekeyshare.OnekeyShare;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu_right,container,false);
        mSharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        islogin = mSharedPreferences.getBoolean("islogin",false);
        relativeLayout_unlogin = (RelativeLayout) mView.findViewById(R.id.relativelayout_unlogin);
        relativeLayout_logined = (RelativeLayout) mView.findViewById(R.id.relativelayout_logined);

        //初始化分享功能控件
        iv_friend = (ImageView) mView.findViewById(R.id.fun_friend);
        iv_qq = (ImageView) mView.findViewById(R.id.fun_qq);
        iv_friends = (ImageView) mView.findViewById(R.id.fun_friends);
        iv_weibo = (ImageView) mView.findViewById(R.id.fun_weibo);

        //给分享功能控件设置监听
        iv_friend.setOnClickListener(L);
        iv_qq.setOnClickListener(L);
        iv_friends.setOnClickListener(L);
        iv_weibo.setOnClickListener(L);
        return mView;
    }

    private View.OnClickListener L = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //判断分享
            switch (view.getId()){
                //分享到微信
                case R.id.fun_friend:
                    showShare(WEBCHAT);
                    break;
                case R.id.fun_qq:
                    showShare(QQ);
                    break;
                case R.id.fun_friends:
                    showShare(WEBCHATMOMENTS);
                    break;
                case R.id.fun_weibo:
                    showShare(SINA);
                    break;

            }
        }
    };

    /**
     * 全部分享界面显示
     *
     * @param 分享的位置
     */
    private void showShare(int platforms){
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        //title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        //titlUrl是标题的网络连接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("Tower新闻客户端");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Tower新闻客户端是一款好的新闻软件");

        switch (platforms){
            case WEBCHAT:
                oks.setPlatform(Wechat.NAME);
                break;
            case WEBCHATMOMENTS:
                oks.setPlatform(WechatMoments.NAME);
                break;
            case QQ:
                oks.setPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
                break;
            case SINA:
                oks.setPlatform(SinaWeibo.NAME);
                break;
        }
        // 启动分享GUI
        oks.show(getActivity());
    }
}
