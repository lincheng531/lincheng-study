package com.lincheng.study.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author linCheng
 * @description: 线程工具类
 * @date 2021/5/28 11:00
 */
public class ThreadUtils {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

    private static ExecutorService taskExe = new ThreadPoolExecutor(10, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);

    public static void runInNewThreadByThreadPoolExecutor(Runnable run) {
        if (run != null) {
            taskExe.execute(run);
        }
    }


    private static ExecutorService threadTool = Executors.newCachedThreadPool();

    public static void runInNewThreadByExecutors(Runnable run) {
        if (run != null) {
            threadTool.execute(run);
        }
    }

}
