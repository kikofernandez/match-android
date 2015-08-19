package se.uu.it.asd.match.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import se.uu.it.asd.match.R;
import se.uu.it.asd.match.beans.RowItemService;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public class CustomListViewAdapter extends ArrayAdapter<RowItemService> {

    protected Context mContext;

    public CustomListViewAdapter(Context context, int resourceId, List<RowItemService> items){
        super(context, resourceId, items);
        this.mContext = context;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItemService rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.title = (TextView) convertView.findViewById(R.id.item_user_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(rowItem.getUser_request());
        holder.desc.setText(rowItem.getRequest());
        holder.imageView.setImageResource(rowItem.getImage());

        return convertView;
    }
}
