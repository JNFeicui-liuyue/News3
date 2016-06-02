package contacts.feicui.edu.news3.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import contacts.feicui.edu.news3.R;
import contacts.feicui.edu.news3.model.entity.SubType;
import contacts.feicui.edu.news3.ui.base.MyBaseAdapter;

/**
 * Created by liuyue on 2016/6/1.
 */
public class NewTypeAdapter extends MyBaseAdapter<SubType> {

    private int selectedPosition;

    public NewTypeAdapter(Context context) {
        super(context);

    }

    @Override
    public View getMyView(int position, View converView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (converView == null){
            converView = mInflater.inflate(R.layout.item_list_typenews,null);
            viewHolder = new ViewHolder(converView);
            converView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) converView.getTag();
        }
        String group = myList.get(position).getSubgroup();
        viewHolder.tv_newstype_type.setText(group);
        if (selectedPosition == position){
            viewHolder.tv_newstype_type.setTextColor(Color.RED);
        }else {
            viewHolder.tv_newstype_type.setTextColor(Color.BLACK);
        }
        return converView;
    }

    public void setSelectedPosition(int position){
        this.selectedPosition = position;
    }

    public class ViewHolder {
        TextView tv_newstype_type;

        public ViewHolder(View view){
            tv_newstype_type = (TextView) view.findViewById(R.id.tv_newstype_type);

        }




    }
}
