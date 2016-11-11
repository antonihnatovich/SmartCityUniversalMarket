package com.l.smartcityuniversalmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.l.smartcityuniversalmarket.loader.LoadAdapter;

public class ShowItemsActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    LoadAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        listView = (ListView)findViewById(R.id.list);
        adapter = new LoadAdapter(this, toLoad); //TODO add links
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        adapter.getImageLoader().clearCache();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy(){
        listView.setAdapter(null);
        super.onDestroy();
    }

    private String[] toLoad = {
            "http://ichef-1.bbci.co.uk/news/ws/660/amz/worldservice/live/assets/images/2015/09/02/150902003640_google_624x351_google_nocredit.jpg",
            "https://www.google.ru/images/branding/googleg/1x/googleg_standard_color_128dp.png"
    };
}
