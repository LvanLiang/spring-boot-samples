package com.liang.base.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * socket客户端
 * @author Liangxp
 * @date 2020/7/22 16:08
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {
        // 建立tcp服务
        Socket socket = new Socket(InetAddress.getLocalHost(), 9091);
        // 获取Socket的输出流对象
        OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
        // 获取到输入流对象，以便读取服务端回送的数据。
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //从控制台获取用户输入的字节流
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        System.out.println("请输入发送给服务器端的数据：");
        while((line = keyReader.readLine())!=null){
            socketOut.write(line+"\r\n"); //给服务器端发送数据
            socketOut.flush();//推送过去
            System.out.println("请输入发送给服务端的信息：");
            line = socketInput.readLine(); //获取服务端返回的数据
            System.out.println("服务器端说：："+line);
        }
        socket.close();
    }
}
