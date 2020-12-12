package com.liang.base.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 * @author Liangxp
 * @date 2020/7/22 16:00
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);
        // 接收客户端的请求，产生一个socket对象
        Socket acceptSocket = serverSocket.accept();
        // 获取Socket的输入流对象，并将输入流的字节对象转换为缓冲字符对象
        BufferedReader socketBufReader = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
        // 获取Socket的输出流对象
        OutputStreamWriter socketWriter = new OutputStreamWriter(acceptSocket.getOutputStream());



        // 从控制台获取服务端要发送的数据
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));

        // 从控制台获取服务端要发送的数据
        String line = null;
        while ((line = socketBufReader.readLine()) != null) {
            System.out.println("客户端说：" + line);
            System.out.println("请输入要发送给客户端的信息：");
            line = keyReader.readLine();
            socketWriter.write(line + "\r\n");
            socketWriter.flush();
        }
        serverSocket.close();
    }
}
