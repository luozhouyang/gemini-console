package me.stupidme.console.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by allen on 18-3-25.
 */

public class UserNameAutoCompleteManager {

    private String mUserNamesFileName = "user_auto_complete_names.txt";

    private static volatile UserNameAutoCompleteManager sInstance;

    private UserNameAutoCompleteManager() {

    }

    public static UserNameAutoCompleteManager getInstance() {
        if (sInstance == null) {
            synchronized (UserNameAutoCompleteManager.class) {
                if (sInstance == null) {
                    sInstance = new UserNameAutoCompleteManager();
                }
            }
        }
        return sInstance;
    }

    public List<String> getHistoryUserNames(Context context) {
        List<String> list = new LinkedList<>();
        try (FileInputStream fis = context.openFileInput(mUserNamesFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
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
