package contacts.feicui.edu.news3.model.biz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.ui.adapter.NewsAdapter1;

/**
 * Created by liuyue on 2016/6/5.
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    //创建chche
    private LruCache<String,Bitmap> mCaches;
    //用来存储要加载到页面上的所有item
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;

    public ImageLoader(ListView listView){
        mListView = listView;
        mTask = new HashSet<NewsAsyncTask>();
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //使用四分之一的缓存空间
        int cacheSize = maxMemory/4;
        mCaches = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //将Bitmap的实际大小返回（在每次存入缓存的时候调用）
                return value.getByteCount();
            }
        };
    }

    //将Bitmap写入缓存中去
    public void addBitmapToCache(String url,Bitmap bitmap){
        //先判断当前缓存中是否包含当前url所指定的图片
        if (getBitmapFromCache(url) == null){
            mCaches.put(url,bitmap);
        }
    }

    //根据url把缓存中的东西读取出来
    public Bitmap getBitmapFromCache(String url){
        return mCaches.get(url);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap) msg.obj);
            //只有当图片地址正确的时候才会加载出来
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

        //使用多线程的方式去加载图片，但是会缓存多次
        public void showImageByThread(ImageView imageView, final String url) {
            mImageView = imageView;
            mUrl = url;

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Bitmap bitmap = getBitmapFromURL(url);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    mHandler.sendMessage(message);

                }
            }.start();

        }


        public Bitmap getBitmapFromURL(String urlString) {
            Bitmap bitmap;
            InputStream is = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                is = new BufferedInputStream(connection.getInputStream());
                bitmap = BitmapFactory.decodeStream(is);
                connection.disconnect();
//                Thread.sleep(1000);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
// catch (InterruptedException e) {
//                e.printStackTrace();
//            }
                finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    //使用异步方式加载图片
    public void showImageByAsyncTask(ImageView imageView,String url){
        //在下载图片之前先判断缓存中有没有
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            //没有就用异步缓存加载图片，从网上下载
//            new NewsAsyncTask(url).execute(url);
            imageView.setImageResource(R.drawable.defaultpic);
        }else {
            //有的话直接从缓存中取
            imageView.setImageBitmap(bitmap);
        }

    }

    //加载从start到end的所有图片
    public void loadImage(int start,int end){
        for (int i = start; i < end; i++) {
            //获取到了从start到end的所有图片的url
            String url = NewsAdapter1.URLS[i];
            //在下载图片之前先判断缓存中有没有
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                //没有就用异步缓存加载图片，从网上下载
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                //保存在当前活动窗口中
                mTask.add(task);
            }else {
                //有的话直接从缓存中取
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void cancelAllTasks(){
        if (mTask != null){
            for (NewsAsyncTask task:mTask){
                task.cancel(false);
            }
        }

    }

    private class NewsAsyncTask extends AsyncTask<String,Void,Bitmap>{

//        private ImageView mImageView;
        private String mUrl;

        public NewsAsyncTask(String url){
//            mImageView = imageView;
            mUrl = url;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            //从网络下载图片
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null ){
                //将下载的图片存入缓存中
                addBitmapToCache(url,bitmap);

            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //只有当图片地址正确的时候才会加载出来
//            if (mImageView.getTag().equals(mUrl)) {
//                mImageView.setImageBitmap(bitmap);
//            }
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }

}
