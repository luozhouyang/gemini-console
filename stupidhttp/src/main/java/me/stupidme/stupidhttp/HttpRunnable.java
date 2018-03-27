package me.stupidme.stupidhttp;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by allen on 18-3-27.
 */

class HttpRunnable implements Runnable {
    private HttpRequest mRequest;
    private RequestCallback mCallback;

    HttpRunnable(@NonNull HttpRequest request, @NonNull RequestCallback callback) {
        mRequest = request;
        mCallback = callback;
    }

    @Override
    public void run() {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            conn = mRequest.getConnection();
            if (conn == null) {
                mCallback.onException(new NullPointerException("Http request connection is null."));
                return;
            }
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                mCallback.onException(new NetworkErrorException());
                return;
            }
            inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            String result = builder.toString();
            mCallback.onSuccess(result);
        } catch (IOException e) {
            mCallback.onException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
