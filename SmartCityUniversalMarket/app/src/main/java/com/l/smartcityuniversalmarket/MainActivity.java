package com.l.smartcityuniversalmarket;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.net.InternetDomainName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button toShop, toBasket, shopHistory, settings, changeUser, saveAndQuite;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toShop = (Button) findViewById(R.id.am_button1);
        //toShop.setOnClickListener(this);
        toBasket = (Button) findViewById(R.id.am_button2);
        // toBasket.setOnClickListener(this);
        shopHistory = (Button) findViewById(R.id.am_button3);
        // shopHistory.setOnClickListener(this);
        settings = (Button) findViewById(R.id.am_button4);
        //  settings.setOnClickListener(this);
        changeUser = (Button) findViewById(R.id.am_button5);
        saveAndQuite = (Button) findViewById(R.id.am_button6);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId())
//        {
//            case R.id.am_button1: {
//                startActivity(new Intent(MainActivity.this, ProductsActivity.class));
//                break;
//            }
//            case R.id.am_button2: {
//                startActivity(new Intent(MainActivity.this, BasketActivity.class));
//                break;
//            }
//            case R.id.am_button3: {
//                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
//                break;
//            }
//            case R.id.am_button4: {
//                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
//                break;
//            }
//        }
//    }


}
