package me.stupidme.console.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by allen on 18-3-29.
 */
@RunWith(AndroidJUnit4.class)
public class ProcessNameUtilTest {

    @Test
    public void getProcessName() {
        Context context = InstrumentationRegistry.getTargetContext();
        assertEquals("me.stupidme.console", ProcessNameUtil.getProcessName(context));
    }
}