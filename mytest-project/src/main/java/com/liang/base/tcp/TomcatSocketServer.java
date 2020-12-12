package com.liang.base.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Liangxp
 * @date 2020/7/22 16:14
 */
public class TomcatSocketServer extends Thread {

    Socket socket;

    public TomcatSocketServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream outSocket;
        try {
            outSocket = socket.getOutputStream();
            String html = "<html><head><title>TomCat测试</title></head><body>哈哈哈哈，测试成功了</body></html>";
            outSocket.write(html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true) {
            Socket socket = serverSocket.accept();
            new TomcatSocketServer(socket).start();
        }

    }
}