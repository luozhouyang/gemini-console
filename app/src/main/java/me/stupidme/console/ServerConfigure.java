package me.stupidme.console;

/**
 * Created by allen on 18-4-30.
 */
public class ServerConfigure {

    public static final int DEV_MODE = 0x01;
    public static final int PROD_MODE = 0x02;

    private static int sMode = DEV_MODE;

    public static void setMode(int mode) {
        if (mode == PROD_MODE) {
            sMode = PROD_MODE;
        } else {
            sMode = DEV_MODE;
        }
    }

    public static int getMode() {
        return sMode;
    }

    private static final String SERVER_ADDRESS_DEV = "http://localhost:8080";
    private static final String SERVER_ADDRESS_PROD = "http://116.85.35.192:8000";

    public static String getServerAddress() {
        if (sMode == DEV_MODE) {
            return SERVER_ADDRESS_DEV;
        } else if (sMode == PROD_MODE) {
            return SERVER_ADDRESS_PROD;
        }
        return "";
    }

    public static String getAndroidPath() {
        return "/android";
    }

    public static String getPythonPath() {
        return "/python";
    }

    public static String getJavaPath() {
        return "/java";
    }

    public static String getGolangPath() {
        return "/go";
    }

    public static String getAndroidURL() {
        return getServerAddress() + getAndroidPath();
    }

    public static String getJavaURL() {
        return getServerAddress() + getJavaPath();
    }

    public static String getPythonURL() {
        return getServerAddress() + getPythonPath();
    }

    public static String getGolangURL() {
        return getServerAddress() + getGolangPath();
    }

    public static String getLoginURL() {
        return getServerAddress() + "/login";
    }

    public static String getLogoutURL() {
        return getServerAddress() + "/logout";
    }

    public static String getSignUpURL() {
        return getServerAddress() + "/signup";
    }

}
