package com.l.smartcityuniversalmarket.http;

import java.io.InputStream;

public interface IHttpClient {

    InputStream get(IRequest request);

    InputStream post(IRequest request);

    public interface IRequest {}
}
