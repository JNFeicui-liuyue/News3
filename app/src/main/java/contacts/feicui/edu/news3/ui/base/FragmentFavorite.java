package contacts.feicui.edu.news3.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import contacts.feicui.edu.news3.R;

/**
 * Created by liuyue on 2016/6/1.
 */
public class FragmentFavorite extends Fragment {
    private View mView;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_favorite,container,false);
        return mView;
    }
}
