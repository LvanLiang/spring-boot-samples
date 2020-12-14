package com.bohoog.util;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Liangxp
 * @date 2019/11/23 14:10
 */
public class ThreadUtils<T> {
    /**
     * 多线程处理list
     * @param data           数据list
     * @param countDownLatch 协调多个线程之间的同步
     * @param threadNum      开启的线程数:也可以使用countDownLatch.getCount();
     */
    public synchronized void handleList(List<T> data, CountDownLatch countDownLatch, int threadNum) {
        // 获取数据的总数
        int length = data.size();
        // 计算每个线程平均处理的数据
        int tl = length % threadNum == 0 ? length / threadNum : (length / threadNum + 1);
        for (int i = 0; i < threadNum; i++) {
            //获得每个线程的最后下标(避免有余数导致数据错误所以前面的线程下标+1)
            int end = (i + 1) * tl;
            List<T> subList = data.subList( i * tl, end > length ? length : end);
        }
    }

}
