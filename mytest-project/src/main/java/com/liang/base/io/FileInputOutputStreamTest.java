package com.liang.base.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件流【字节】的输入输出
 * @author Liangxp
 * @date 2020/7/24 11:25
 */
public class FileInputOutputStreamTest {

    @Test
    public void testFileInputOutputStream() {
        String srcPath = "src\\main\\resources\\practice\\dm.jpg";
        String destPath = "src\\main\\resources\\practice\\dm-copy22.jpg";
        long start = System.currentTimeMillis();
        copyFile(srcPath, destPath);
        long end = System.currentTimeMillis();
        System.out.println("文件copy结束，耗时：" + (end -start));
    }

    @Test
    public void testVideoCopy() {
        String srcPath = "C:\\Users\\liangxp\\Desktop\\Feign.mp4";
        String destPath = "C:\\Users\\liangxp\\ccccc\\01-copy.mp4";
        long start = System.currentTimeMillis();
        copyFile(srcPath, destPath);
        long end = System.currentTimeMillis();
        System.out.println("视频copy结束，耗时：" + (end -start));

    }

    /**
     * 使用字节流，拷贝文件
     * @param srfPath 原文件路径
     * @param destPath 目标文件路径
     */
    public void copyFile(String srfPath, String destPath) {
        File srcFile = new File(srfPath);
        File destFile = new File(destPath);

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int length;
            while((length = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
