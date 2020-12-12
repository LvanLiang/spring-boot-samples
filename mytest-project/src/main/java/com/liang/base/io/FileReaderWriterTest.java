package com.liang.base.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件流【字符】的输入输出
 * @author Liangxp
 * @date 2020/7/24 10:05
 */
@Slf4j
public class FileReaderWriterTest {

    @Test
    public void dirCanWrite() throws IOException {
        File targetFile = new File("D:/360Downloads/advfvf/readme.png");
        File file = new File("D:/360Downloads/tmp/tt.png");
    }

    @Test
    public void test1() {
        int a = 115;
        System.out.println((char)a);
    }

    @Test
    public void testFileReader() throws IOException {
        // 实例化File类的对象，指明要操作的文件
        File file = new File("src\\main\\resources\\application.yml");
        // 提供具体的流
        FileReader fileReader = new FileReader(file);

        // 数据的读入； read():返回读入的一个字符，如果达到文件末尾，返回-1
        int data = fileReader.read();
        while(data != -1) {
            System.out.print((char) data);
            data = fileReader.read();
        }

        // 流的关闭操作
        fileReader.close();
    }

    @Test
    public void testFileReader2() throws IOException {
        File file = new File("src\\main\\resources\\practice\\hello3.txt");
        FileReader fileReader = new FileReader(file);
        char[] bufferChar = new char[5];
        int length;
        while ((length = fileReader.read(bufferChar)) != -1) {
            // 方式一
          /*  for (int i = 0; i < length; i++) {
                System.out.print(bufferChar[i]);
            }*/

            // 方式二
            String str = new String(bufferChar, 0, length);
            System.out.print(str);
        }
    }

    @Test
    public void testFileWriter() {
        FileWriter fileWriter = null;
        try {
            File file = new File("src\\main\\resources\\practice\\hello2.txt");
            fileWriter = new FileWriter(file, true);
            fileWriter.write("I have a dream! \n");
            fileWriter.write("you need to hava a dream");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
