package me.stupidme.stupidhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by allen on 18-3-26.
 */

public class StupidHttp {

    private ExecutorService mThreadPool;

    private StupidHttp() {
        mThreadPool = new ThreadPoolExecutor(
                1,
                4,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());
    }

    private static class StupidHttpHolder {
        private static final StupidHttp INSTANCE = new StupidHttp();
    }

    public static StupidHttp getInstance() {
        return StupidHttpHolder.INSTANCE;
    }

    public void go(Request request, RequestCallback callback) {
        mThreadPool.submit(new RequestRunnable(request, callback));
    }
}
