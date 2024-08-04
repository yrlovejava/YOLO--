package book.manage;

import book.manage.client.Client;
import book.manage.sever.Sever;

import java.io.IOException;

//这是启动类，你直接运行这个就可以
public class Main {
    public static void main(String[] args) {
        // 启动服务器
        Thread serverThread = new Thread(() -> {
            try {
                Sever.main(null); // 在这里调用 Server 类的 main 方法
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // 启动客户端
        Thread clientThread = new Thread(() -> {
            try {
                Client.main(null); // 在这里调用 Client 类的 main 方法
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        clientThread.start();
    }
}