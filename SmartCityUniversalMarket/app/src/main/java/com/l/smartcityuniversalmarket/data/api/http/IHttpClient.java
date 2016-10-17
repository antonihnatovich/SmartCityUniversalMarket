package com.l.smartcityuniversalmarket.data.api.http;

import java.io.InputStream;

public interface IHttpClient {
    InputStream get(IRequest pRequest);

    InputStream post(IRequest pRequest);

    interface IRequest {

    }
}
