package me.stupidme.console.utils;

/**
 * Created by allen on 18-3-25.
 */

public class TokenManager {

    private static volatile TokenManager sInstance;

    private String mKey;
    private static final String TOKEN = "";

    private TokenManager() {

    }

    public static TokenManager getInstance() {
        if (sInstance == null) {
            synchronized (TokenManager.class) {
                if (sInstance == null) {
                    sInstance = new TokenManager();
                }
            }
        }
        return sInstance;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getToken() {
        if (mKey == null || mKey.isEmpty()) {
            LoggerProxy.w(TokenManager.class.getCanonicalName(), "Key is empty. You need " +
                    "specify a key first.");
            return "";
        }
        return SecurityUtil.decryptDES(TOKEN, mKey);
    }
}
