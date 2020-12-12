package com.liang.base.io;

import org.junit.Test;

import java.io.*;

/**
 * 转换流
 * 字节流→字符流，字符流→字节流
 * 转换流的作用：
 * 1. 可以把对应的字节流转换成字符流使用。
 * 2. 可以指定码表进行读写文件的数据。
 *
 * @author Liangxp
 * @date 2020/8/12 11:09
 */
public class InputStreamReaderTest {

    @Test
    public void testInputStreamReader() throws IOException {
        // 输入字节流
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\practice\\hello3.txt");
        // 将字节流按照编码转换为字符的转换流（解码）
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "gbk");
        char[] chars = new char[5];
        int length;
        while ((length = inputStreamReader.read(chars)) != -1) {
            String txt = new String(chars, 0, length);
            System.out.print(txt);
        }

    }


    /**
     * 转换流的综合使用
     */
    @Test
    public void test() throws Exception {
        File utfFile = new File("src\\main\\resources\\practice\\hello.txt");
        File gbkFile = new File("src\\main\\resources\\practice\\gbk-hello.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(utfFile), "utf-8");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(gbkFile), "gbk");

        char[] chars = new char[5];
        int length;
        while ((length = inputStreamReader.read(chars)) != -1) {
            outputStreamWriter.write(chars,0, length);
        }
        inputStreamReader.close();
        outputStreamWriter.close();

    }

}
