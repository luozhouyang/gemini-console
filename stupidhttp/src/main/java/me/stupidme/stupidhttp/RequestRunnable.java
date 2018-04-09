package me.stupidme.stupidhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by allen on 18-4-9.
 */
public class RequestRunnable implements Runnable {

    private Request mRequest;
    private RequestCallback mCallback;

    RequestRunnable(Request request, RequestCallback callback) {
        this.mRequest = request;
        this.mCallback = callback;
    }

    @Override
    public void run() {
        if (mRequest == null) {
            System.out.println("Request object is null. Request abort.");
            return;
        }
        try {
            HttpURLConnection connection = mRequest.getConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                if (mCallback != null) {
                    mCallback.onException(new Exception("Bad response. Code: " + connection.getResponseCode()));
                }
                return;
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            if (mCallback != null) {
                mCallback.onSuccess(builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (mCallback != null) {
                mCallback.onException(e);
            }
        }
    }
}
