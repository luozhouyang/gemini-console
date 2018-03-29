package me.stupidme.console.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by allen on 18-3-29.
 */
public class ProcessNameUtil {

    public static String getProcessName(Context context) {
        String processName = "";
        int pid = android.os.Process.myPid();
        File file = new File("/proc/" + pid + "/cmdline");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "iso-8859-1"))) {
            int c;
            StringBuilder builder = new StringBuilder();
            while ((c = reader.read()) > 0) {
                builder.append((char) c);
            }
            processName = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!processName.isEmpty()) {
            return processName;
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return processName;
        }
        for (ActivityManager.RunningAppProcessInfo info : manager.getRunningAppProcesses()) {
            if (info.pid == pid) {
                processName = info.processName;
                break;
            }
        }
        return processName;
    }
}
