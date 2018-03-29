package me.stupidme.console.account;

/**
 * Created by allen on 18-3-29.
 */
public class Account {

    private String mUserName;
    private String mPassword;

    public Account(String name, String pass) {
        mUserName = name;
        mPassword = pass;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

}
