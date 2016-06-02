package contacts.feicui.edu.news3.ui.base;

import android.support.v4.app.Fragment;

import java.util.List;

import contacts.feicui.edu.news3.model.biz.parser.ParserNews;
import contacts.feicui.edu.news3.model.entity.SubType;
import contacts.feicui.edu.news3.model.volley.Response;

/**新闻列表界面
 * Created by liuyue on 2016/6/1.
 */
public class FragmentMain extends Fragment{
    class VolleyTypeReponseHandler implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            List<SubType> types = ParserNews
        }
    }
}
