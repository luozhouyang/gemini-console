package me.stupidme.console.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

/**
 * Created by allen on 18-3-25.
 */

public class UserInfoManager {

    private static WeakReference<Context> mContextRef;

    private static volatile UserInfoManager sInstance;

    private SharedPreferences mSharedPreference;

    //TODO(luozhouyang) dynamic set key by pop a dialog.
    private String mKey = "0123456789abcefghijklmno";

    private UserInfoManager() {
        mSharedPreference = mContextRef.get().getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        mContextRef = new WeakReference<>(context.getApplicationContext());
    }

    public static UserInfoManager getInstance() {
        if (mContextRef == null || mContextRef.get() == null) {
            LoggerProxy.e(UserInfoManager.class.getCanonicalName(), "Context is null. Make sure " +
                    "you have init it before you get an instance.");
            return null;
        }
        if (sInstance == null) {
            synchronized (UserInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new UserInfoManager();
                }
            }
        }
        return sInstance;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getUserName() {
        String s = mSharedPreference.getString("user_name", "");
        return SecurityUtil.decryptDES(s, mKey);
    }

    public String getPassword() {
        String s = mSharedPreference.getString("password", "");
        return SecurityUtil.decryptDES(s, mKey);
    }

    public void setUserName(String name) {
        String s = SecurityUtil.encryptDES(name, mKey);
        mSharedPreference.edit().putString("user_name", s).commit();
    }

    public void setPassword(String password) {
        String s = SecurityUtil.encryptDES(password, mKey);
        mSharedPreference.edit().putString("password", s).commit();
    }
}
