/**
 * www.juanpi.com Inc.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package com.base.utils;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 处理线程池执行器
 *
 * @author : David.Song
 * @company : www.juanpi.com
 * @department : 基础服务部/JAVA工程师
 * @date : 2015年12月1日 下午5:17:12
 * @see
 * @since : 1.0.0
 */
public final class ExecutorUtil {

    /**
     * 线程池
     */
    private ExecutorService executorService;

    /**
     * 构造函数
     */
    private ExecutorUtil() {
        executorService = Executors.newFixedThreadPool(30);
    }

    /**
     * 单例
     *
     * @return 获取单例
     */
    public static ExecutorUtil getInstance() {
        return HandlerExecutorsHolder.handlerExecutor;
    }

    /**
     * 执行线程
     *
     * @param command 命令
     */

    public Future<?> submit(Runnable command) {
        return executorService.submit(command);
    }

    public void execute(Runnable command) {
        executorService.execute(command);
    }

    public void invokeAll(Collection<? extends Callable<Object>> tasks) {
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitCallable(Callable<Integer> callable) {
        executorService.submit(callable);
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public boolean isShutdown() {
        return getInstance().isShutdown();
    }

    private static class HandlerExecutorsHolder {
        private static ExecutorUtil handlerExecutor = new ExecutorUtil();
    }
}
