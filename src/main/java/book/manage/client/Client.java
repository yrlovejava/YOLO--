package book.manage.client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

//客户端
//基于命令行的图书管理系统
public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("连接到客户端");
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw;
        BufferedReader br;

        System.out.println("===========欢迎来到图书管理系统=============");
        System.out.println("请先登录");
        System.out.println("输入其他任意键退出");

        String s = sc.nextLine();
        if ("1".equals(s)) {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write("用户登录", bw);
            int count = 3;

            while (count >= 0) {
                System.out.println("请输入你的用户名");
                String inputName = sc.nextLine();
                System.out.println("请输入你的密码");
                String inputPassword = sc.nextLine();

                //以用户名&密码的格式传递给服务端
                String info = inputName + "&" + inputPassword;
                write(info, bw);


                //等待反馈
                if ("登录失败".equals(br.readLine())) {
                    if (--count == 0) {
                        System.out.println("您的账号已被锁定，请稍后再试");
                        System.exit(0);
                    }
                } else {
                    System.out.println("登录成功");
                    break;
                }
            }

            //具体的操作
            while (true) {
                System.out.println("================================");
                System.out.println("1.查找书籍");
                System.out.println("2.借阅书籍");
                System.out.println("3.归还书籍");
                System.out.println("输入你想要执行的操作(输入任意键退出)");

                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        write("查找书籍", bw);
                        searchBook(sc, bw, br);
                        break;
                    case "2":
                        write("借阅书籍", bw);
                        addBorrow(sc, bw, br);
                        break;
                    case "3":
                        write("归还书籍",bw);
                        returnBook(sc,bw,br);
                        break;
                    default:
                        System.exit(0);
                }
            }


        } else {
            socket.close();
            System.exit(0);
        }
    }

    private static void write(String message, BufferedWriter bw) throws IOException {
        bw.write(message);
        bw.newLine();
        bw.flush();
    }


    private static void searchBook(Scanner sc, BufferedWriter bw, BufferedReader br) throws IOException {
        System.out.println("请输入你要查找书籍的名字");
        String title = sc.nextLine();
        write(title, bw);

        //获取信息
        String str;
        while ((str = br.readLine()) != null) {
            if ("over".equals(str)) {
                break;
            }
            System.out.println(str);
        }
    }

    private static void addBorrow(Scanner sc, BufferedWriter bw, BufferedReader br) throws IOException {
        System.out.println("请输入你的学号");
        String id = sc.nextLine();
        System.out.println("请输入你要借阅的书籍id");
        String book_id = sc.nextLine();

        String info = id + "&" + book_id;
        write(info, bw);

        //接受反馈
        System.out.println(br.readLine());
    }

    private static void returnBook(Scanner sc,BufferedWriter bw, BufferedReader br) throws IOException {
        System.out.println("请输入你的学号");
        String id = sc.nextLine();
        System.out.println("请输入你要归还的书籍id");
        String book_id = sc.nextLine();

        String info = id + "&" + book_id;
        write(info, bw);

        //接受反馈
        System.out.println(br.readLine());
    }
}
