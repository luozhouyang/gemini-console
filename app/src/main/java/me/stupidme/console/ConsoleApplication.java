package me.stupidme.console;

import android.app.Application;

import me.stupidme.console.utils.UserInfoManager;


/**
 * Created by allen on 18-3-21.
 */

public class ConsoleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserInfoManager.init(this);
    }
}
