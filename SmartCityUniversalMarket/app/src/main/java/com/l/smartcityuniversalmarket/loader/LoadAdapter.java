package com.l.smartcityuniversalmarket.loader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l.smartcityuniversalmarket.R;

public class LoadAdapter extends BaseAdapter {
    private Activity activity;
    private String[] data;
    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;

    public LoadAdapter(Activity activity, String[] d) {
        this.activity = activity;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.item, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        String str = "item" + position;
        text.setText(str);
        imageLoader.displayImage(data[position], image);
        return view;
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
