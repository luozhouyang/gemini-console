package me.stupidme.console.account;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import me.stupidme.console.utils.LoggerProxy;

/**
 * Created by allen on 18-3-29.
 */
public class AccountManagerImpl implements AccountManager {

    private static final String TAG = AccountManagerImpl.class.getCanonicalName();
    private static final String mAccountFileName = "accounts.txt";

    public static class AccountManagerHolder {
        private static final AccountManagerImpl INSTANCE = new AccountManagerImpl();
    }

    public static AccountManagerImpl getInstance() {
        return AccountManagerHolder.INSTANCE;
    }

    private AccountManagerImpl() {

    }

    @Override
    public void setAccount(Context context, Account account) {
        if (context == null) {
            return;
        }
        try (FileOutputStream fos = context.openFileOutput(mAccountFileName, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            String line = account.getUserName() + "@@@@@" + account.getPassword();
            writer.write(line);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccount(Context context) {
        if (context == null) {
            return null;
        }
        try (FileInputStream fis = context.openFileInput(mAccountFileName);
             InputStreamReader reader = new InputStreamReader(fis)) {
            int c;
            StringBuilder builder = new StringBuilder();
            while ((c = reader.read()) > 0) {
                builder.append((char) c);
            }
            String line = builder.toString();
            String[] a = line.split("@@@@@");
            if (a.length != 2) {
                return null;
            }
            return new Account(a[0], a[1]);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerProxy.e(TAG, "Get account failed: " + e.getMessage());
        }
        return null;
    }

}
