package me.stupidme.stupidhttp;

/**
 * Created by allen on 18-3-27.
 */

public interface RequestCallback {

    void onException(Exception e);

    void onSuccess(String response);
}
