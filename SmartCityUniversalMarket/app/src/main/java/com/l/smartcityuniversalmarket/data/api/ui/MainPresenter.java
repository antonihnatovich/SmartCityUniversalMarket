package com.l.smartcityuniversalmarket.data.api.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.l.smartcityuniversalmarket.BackendTestActivity;
import com.l.smartcityuniversalmarket.backend.smartCityUniversalMarketAPI.SmartCityUniversalMarketAPI;
import com.l.smartcityuniversalmarket.data.api.api.ApiManager;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MainPresenter implements Contract.Presenter {
    private Contract.View view;
    private Handler handler;

    public MainPresenter(@NonNull Contract.View view) {
        this.view = view;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onReady() {
        view.showProgress(true);
        loadData();
    }

    private void loadData() {

        new Thread() {
            @Override
            public void run() {

                try {
                    SmartCityUniversalMarketAPI.GetStats call = ApiManager.get().trainingsApi().getStats();
                    String response = call.execute().getData();
                    notifyResponse(response);
                } catch (IOException e) {
                    Log.e(TAG, "run: ", e);
                    notifyError(e);
                }
            }
        }.start();
    }

    private void notifyResponse(final String response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.showProgress(false);
                view.showData(response);
            }
        });
    }

    private void notifyError(final Throwable e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.showProgress(false);
                view.showError(e.getMessage());
            }
        });
    }



}
