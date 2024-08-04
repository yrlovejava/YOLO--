package book.manage.sever;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//服务端
public class Sever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            System.out.println("服务端在端口9999等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            new Thread(new MyRunnable(socket)).start();
            //登录一个新开一个线程
            // 你不需要管
        }
    }
}
