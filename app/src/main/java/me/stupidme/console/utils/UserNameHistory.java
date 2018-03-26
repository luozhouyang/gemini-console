package me.stupidme.console.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by allen on 18-3-25.
 */

public class UserNameHistory {

    private String mUserNamesFileName = "user_auto_complete_names.txt";

    private static volatile UserNameHistory sInstance;

    private UserNameHistory() {

    }

    public static UserNameHistory getInstance() {
        if (sInstance == null) {
            synchronized (UserNameHistory.class) {
                if (sInstance == null) {
                    sInstance = new UserNameHistory();
                }
            }
        }
        return sInstance;
    }

    public Set<String> getHistoryUserNames(Context context) {
        Set<String> set = new TreeSet<>();
        try (FileInputStream fis = context.openFileInput(mUserNamesFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                set.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    public void addUserName(Context context, String name) {
        try (FileOutputStream fos = context.openFileOutput(mUserNamesFileName, Context.MODE_APPEND);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            writer.write(name);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
