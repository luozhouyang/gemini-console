package me.stupidme.stupidhttp;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by allen on 18-4-9.
 */
public abstract class Request {

    private static final int DEFAULT_TIMEOUT = 10 * 1000;
    private int readTimeout;
    private int connectTimeout;
    private String url;

    public Request(String url, int readTimeout, int connectTimeout) {
        this.readTimeout = readTimeout;
        this.connectTimeout = connectTimeout;
        this.url = url;
    }

    public Request(String url) {
        this(url, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    public abstract HttpURLConnection getConnection() throws IOException;

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
