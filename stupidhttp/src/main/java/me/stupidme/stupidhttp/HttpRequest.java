package me.stupidme.stupidhttp;

import android.net.Uri;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by allen on 18-3-27.
 */

public class HttpRequest {

    private String mMethod;
    private long mReadTimeout;
    private long mConnectTimeout;
    private String mUrl;
    private Map<String, String> mGetRequestParams;
    private Map<String, String> mPostRequestForm;

    private HttpRequest() {

    }

    public HttpURLConnection getConnection() throws IOException {
        switch (mMethod.toUpperCase()) {
            case "GET":
                return createGetConnection();
            case "POST":
                return createPostConnection();
            default:
                return null;
        }
    }

    private HttpURLConnection createGetConnection() throws IOException {
        String params = buildGetRequestParamsString();
        URL url = new URL(mUrl + params);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout((int) mReadTimeout);
        conn.setConnectTimeout((int) mConnectTimeout);
        conn.setRequestMethod(mMethod);
        conn.setDoInput(true);
        conn.connect();
        return conn;
    }

    private HttpURLConnection createPostConnection() throws IOException {
        URL url = new URL(mUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout((int) mReadTimeout);
        conn.setConnectTimeout((int) mConnectTimeout);
        conn.setRequestMethod(mMethod);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream outputStream = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        String form = buildPostRequestFormString();
        writer.write(form);
        writer.flush();
        writer.close();
        outputStream.close();

        conn.connect();
        return conn;
    }

    private String buildPostRequestFormString() {
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, String> entry : mPostRequestForm.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.appendQueryParameter(key, value);
        }
        return builder.build().getEncodedQuery();
    }

    private String buildGetRequestParamsString() throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : mGetRequestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.append(URLEncoder.encode(key, "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(value, "UTF-8"))
                    .append("&");
        }
        if (builder.toString().isEmpty()) {
            return builder.toString();
        }
        builder.insert(0, "?");
        return builder.substring(0, builder.length() - 1);
    }

    public String getRequestMethod() {
        return mMethod;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public String getUrl() {
        return mUrl;
    }

    public Map<String, String> getGetRequestParams() {
        return mGetRequestParams;
    }

    public Map<String, String> getPostRequestForm() {
        return mPostRequestForm;
    }

    public static class Builder {
        private String method = "GET";
        private long readTimeout = 3 * 1000;
        private long connectTimeout = 3 * 1000;
        private String url = "";
        private Map<String, String> getRequestParams;
        private Map<String, String> postRequestForm;
        private HttpGetParams params;
        private HttpPostForm form;

        public Builder() {

        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder readTimeout(long milliseconds) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder connectTimeout(long milliseconds) {
            this.connectTimeout = milliseconds;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder getRequestParams(Map<String, String> params) {
            this.getRequestParams = params;
            return this;
        }

        public Builder getRequestParams(HttpGetParams params) {
            this.params = params;
            return this;
        }

        public Builder postRequestForm(Map<String, String> form) {
            this.postRequestForm = form;
            return this;
        }

        public Builder postRequestForm(HttpPostForm form) {
            this.form = form;
            return this;
        }

        public HttpRequest build() {
            HttpRequest request = new HttpRequest();
            request.mMethod = this.method;
            request.mReadTimeout = this.readTimeout;
            request.mConnectTimeout = this.connectTimeout;
            request.mUrl = this.url;
            if (request.mGetRequestParams == null) {
                request.mGetRequestParams = new HashMap<>(4);
            }
            if (this.params != null) {
                request.mGetRequestParams.putAll(this.params.parseParams());
            }
            if (this.getRequestParams != null) {
                request.mGetRequestParams.putAll(this.getRequestParams);
            }
            if (request.mPostRequestForm == null) {
                request.mPostRequestForm = new HashMap<>(8);
            }
            if (this.form != null) {
                request.mPostRequestForm.putAll(this.form.parseForm());
            }
            if (this.postRequestForm != null) {
                request.mPostRequestForm.putAll(this.postRequestForm);
            }
            return request;
        }
    }
}
