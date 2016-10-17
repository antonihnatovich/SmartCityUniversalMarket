/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.l.smartcityuniversalmarket.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
        name = "smartCityUniversalMarketAPI",
        version = "2016",
        namespace = @ApiNamespace(
                ownerDomain = "backend.smartcityuniversalmarket.l.com",
                ownerName = "backend.smartcityuniversalmarket.l.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    public static final String TIME_URL = "http://www.timeapi.org/utc/now";

    @ApiMethod(name = "getStats")
    public MyBean getStats() {
        MyBean response = new MyBean();
        String data = new HttpClient().get(TIME_URL);
        response.setData(data);
        return response;
    }

}
