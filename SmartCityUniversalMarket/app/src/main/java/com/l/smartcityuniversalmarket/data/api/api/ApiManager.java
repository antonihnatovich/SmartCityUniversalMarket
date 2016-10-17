package com.l.smartcityuniversalmarket.data.api.api;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.l.smartcityuniversalmarket.BuildConfig;
import com.l.smartcityuniversalmarket.backend.smartCityUniversalMarketAPI.SmartCityUniversalMarketAPI;

import java.io.IOException;

public class ApiManager {
    public static final String APP_ENGINE_BASE_URL = "http://10.0.2.2:8080/_ah/api/";

    private static ApiManager sInstance;
    private SmartCityUniversalMarketAPI appEngineApi;

    public static ApiManager get() {
        if (sInstance == null) {
            sInstance = new ApiManager();
        }
        return sInstance;
    }

    private ApiManager() {
    }

    public SmartCityUniversalMarketAPI trainingsApi() {
        if (appEngineApi == null) {
            SmartCityUniversalMarketAPI.Builder builder = new SmartCityUniversalMarketAPI.Builder(AndroidHttp.newCompatibleTransport(),
                    JacksonFactory.getDefaultInstance(), null)
                    .setRootUrl(APP_ENGINE_BASE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            appEngineApi = builder.build();
        }
        return appEngineApi;
    }
}
