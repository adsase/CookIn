package dam2.sixapp.cookin.customList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dam2.sixapp.cookin.R;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.textViewDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem newsItem = (NewsItem)listData.get(position);
        holder.titleView.setText(newsItem.getTitle());
        holder.descriptionView.setText(newsItem.getDesc());
        //holder.imageView.setImageResource(newsItem.getImage());


        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView descriptionView;
        ImageView imageView;

    }

}
