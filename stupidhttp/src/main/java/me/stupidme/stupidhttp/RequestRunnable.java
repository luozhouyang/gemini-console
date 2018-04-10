package me.stupidme.stupidhttp;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.List;

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
            CookieManager manager = new CookieManager();
            if (manager.getCookieStore().getCookies().size() > 0) {
                connection.setRequestProperty("Cookie",
                        TextUtils.join(";", manager.getCookieStore().getCookies()));
            }
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                if (mCallback != null) {
                    mCallback.onException(new Exception("Bad response. Code: " + connection.getResponseCode()));
                }
                return;
            }
            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
            if (cookies != null) {
                for (String cookie : cookies) {
                    manager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                }
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
