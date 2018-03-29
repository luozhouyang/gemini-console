package me.stupidme.console.account;

import android.content.Context;

/**
 * Created by allen on 18-3-29.
 */
public interface AccountManager {

    void setAccount(Context context,Account account);

    Account getAccount(Context context);
}
