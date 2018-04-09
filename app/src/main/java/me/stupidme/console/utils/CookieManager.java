package me.stupidme.console.utils;

/**
 * There has only one cookie from server.
 * Save it in memory, so each time opening this application, we need to login
 * to server to get cookie, then save it.
 * For every http request excepting login, we should set cookie for the http request.
 * <p>
 * Created by allen on 18-4-9.
 */
public class CookieManager {

    private static String sCookie;

    private CookieManager() {

    }

    private static class Holder {
        private static final CookieManager INSTANCE = new CookieManager();
    }

    public static CookieManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCookie(String cookie) {
        sCookie = cookie;
    }

    public synchronized String getCookie() {
        return sCookie;
    }
}
