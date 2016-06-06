package contacts.feicui.edu.news3.ui.base;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.common.SystemUtils;
import contacts.feicui.edu.news3.model.entity.News;

/**新闻的具体界面
 * Created by liuyue on 2016/6/5.
 */
public class ActivityShow extends MyBaseActivity{

    private WebView webView;
    private ProgressBar progressBar;
    private TextView tv_commentCount;
    private ImageView imageViewBack;
    private ImageView imageViewMenu;
    private News newsitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //没连上网显示加载失败
        if (!SystemUtils.getInstance(this).isNetConn()){
            setContentView(R.layout.oh_no);
        }else {
            //显示新闻
            setContentView(R.layout.activity_show);
            tv_commentCount = (TextView) findViewById(R.id.textView2);//跟帖
            progressBar = (ProgressBar) findViewById(R.id.progressBar1);
            webView = (WebView) findViewById(R.id.webView1);
            imageViewBack = (ImageView) findViewById(R.id.imageView_back);
            imageViewMenu = (ImageView) findViewById(R.id.imageView_menu);//加入收藏（三个点）
            newsitem = (News) getIntent().getSerializableExtra("newsitem");
            webView.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
            WebChromeClient client = new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    progressBar.setProgress(newProgress);
                    if (newProgress >= 100){
                        progressBar.setVisibility(View.GONE);
                    }
                }
            };
            webView.setWebChromeClient(client);
            webView.loadUrl(newsitem.link);

//            tv_commentCount.setOnClickListener(clickListener);
//            imageViewBack.setOnClickListener(clickListener);
//            imageViewMenu.setOnClickListener(clickListener);

        }
    }

//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.imageView_back:
//                    finish();
//                    break;
//                case R.id.textView2:
//                    Toast.makeText(ActivityShow.this,"跟帖",Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.imageView_menu:
//                    Toast.makeText(ActivityShow.this,"加入收藏",Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
}
