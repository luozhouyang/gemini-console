package me.stupidme.console;

import android.app.Application;

import me.stupidme.console.utils.LoggerProxy;
import me.stupidme.console.utils.ProcessNameUtil;


/**
 * Created by allen on 18-3-21.
 */

public class ConsoleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerProxy.setMode(LoggerProxy.MODE_DEBUG);
        String processName = ProcessNameUtil.getProcessName(this);
        if ("me.stupidme.console".equals(processName)) {
            LoggerProxy.d(ConsoleApplication.class.getCanonicalName(), "Process name is:" + processName);
        } else {
            LoggerProxy.e(ConsoleApplication.class.getCanonicalName(),
                    "Can not confirm process name. Exist.");
        }
    }
}
