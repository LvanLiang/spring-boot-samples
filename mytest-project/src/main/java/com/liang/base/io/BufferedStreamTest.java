package com.liang.base.io;

import org.junit.Test;

import java.io.*;

/**
 * 缓冲流的使用
 * @author Liangxp
 * @date 2020/7/24 17:20
 */
public class BufferedStreamTest {

    /**
     * 缓冲流视频拷贝   耗时：4036
     * 文件流(节点流)   耗时：566
     */
    @Test
    public void testCopyFileWithBufferedStream() {
        try {
            String srcPath = "C:\\Users\\liangxp\\Desktop\\Feign.mp4";
            String destPath = "C:\\Users\\liangxp\\Desktop\\02-copy.mp4";
            long start = System.currentTimeMillis();
            copyFileWithBufferedStream(srcPath, destPath);
            long end = System.currentTimeMillis();
            System.out.println("视频copy结束，耗时：" + (end -start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFileWithBufferedStream(String srcPath, String destPath) throws Exception {
        // 1.造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        // 2.造流
        // 2.1造节点流和缓冲流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFile));

        // 3.复制
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
        }

        // 4.资源关闭，先关闭外层的流，在关闭内层的流
        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

}
