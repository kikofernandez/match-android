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

    private String getShortDescription(String description){
        int request_size = description.split(" ").length;
        int max_length = request_size > 10 ? 10 : request_size;
        String[] sentences = description.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<max_length; i++){
            if(i==max_length-1)
                builder.append(sentences[i]+"...");
            else
                builder.append(sentences[i]+" ");
        }
        return builder.toString();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        RowItemService rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.desc = (TextView) convertView.findViewById(R.id.item_desc);
            holder.title = (TextView) convertView.findViewById(R.id.item_user_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(rowItem.getUser_request());
        holder.desc.setText(getShortDescription(rowItem.getRequest()));
        holder.imageView.setImageResource(rowItem.getImage());

        return convertView;
    }
}
