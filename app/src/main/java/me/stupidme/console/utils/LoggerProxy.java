package me.stupidme.console.utils;

import android.util.Log;


/**
 * Created by allen on 18-3-25.
 */

public class LoggerProxy {

    public static final int MODE_DEBUG = 0;
    public static final int MODE_PROD = 1;

    private static int MODE = MODE_DEBUG;

    public static void setMode(int mode) {
        if (mode == 0 || mode == 1) {
            MODE = mode;
            return;
        }
        MODE = MODE_DEBUG;
    }

    public static int getMode() {
        return MODE;
    }

    public static void i(String tag, String msg) {
        if (MODE == MODE_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (MODE == MODE_DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (MODE == MODE_DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (MODE == MODE_DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (MODE == MODE_DEBUG) {
            Log.e(tag, msg);
        }
    }
}
