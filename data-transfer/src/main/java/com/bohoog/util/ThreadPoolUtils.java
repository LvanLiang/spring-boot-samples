package com.bohoog.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Liangxp
 * @date 2019/11/22 17:27
 */
public class ThreadPoolUtils {
    private ThreadPoolUtils() {}

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     */
    private static final int MAXIMUM_POOL_SIZE = 8;
    /**
     * 线程存活时间
     */
    private static final long KEEP_ALIVE_TIME = 15;

    /**
     * 线程池
     */
    private static final ExecutorService EXEC;

    static{
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("process-send-msg-thread-%d").build();
        EXEC = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), threadFactory,  new ThreadPoolExecutor.AbortPolicy());
    }

    public static void execute(final Runnable command) {
        EXEC.execute(command);
    }

    public static void shutdown(){
        EXEC.shutdown();
    }
}
