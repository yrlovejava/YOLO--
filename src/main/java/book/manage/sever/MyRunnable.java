package book.manage.sever;

import book.manage.entity.Book;
import book.manage.mapper.BookMapper;
import book.manage.util.MybatisUtil;
import lombok.Data;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


@Data
public class MyRunnable implements Runnable {
    final Socket socket;

    @Override
    public void run() {
        BufferedReader br; // 输入缓冲流 需要的时候你需要new
        BufferedWriter bw; // 输出缓冲流 需要的时候你需要new
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /**
             * 完成用户登录的功能 客户端会以 用户名&密码的方式传递信息 例如 xiaohong&12345
             */
            String inputName = null;
            String inputPassword = null;
            /*
                TODO 你需要自己去获取inputName 和 inputPassword
             */

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (checkUser(inputName, inputPassword)) {
                writeB("登录成功", bw);
            } else writeB("登录失败", bw);

            while (true) {
                String str = br.readLine();
                if ("查找书籍".equals(str)) {
                    searchBook(br, bw);
                } else if ("借阅书籍".equals(str)) {
                    addBorrow(br, bw);
                } else if ("归还书籍".equals(str)) {
                    returnBook(bw, br);
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO 你需要完成这个函数
     * @param name 输入的用户名
     * @param password 输入的密码
     * @return 是否登录成功
     */
    private boolean checkUser(String name, String password) {
        // 在mapper文件夹下LoginMapper中编写合适的sql语句

        // 使用MybatisUtil中的getLoginMapper()方法获取mapper

        // 去数据库中查询学生是否存在

        // 为了代码不报错而实现
        return false;
    }

    private static void writeB(String message, BufferedWriter bw) throws IOException {
        bw.write(message);
        bw.newLine();
        bw.flush();
    }

    /**
     * 这个函数用于学习示例，可以参照这个函数完成自己的函数
     * @param bw 缓存流
     */
    private static void selectAllBook(BufferedWriter bw) {
        List<Book> books = MybatisUtil.getBookMapper().selectAllBook();

        books.forEach(book -> {
            try {
                writeB("序号:" + book.getId() +
                        ", 书名" + book.getTitle() +
                        ", 简介" + book.getDesc() +
                        ", 价格" + book.getPrice(), bw);

            } catch (IOException e) {
                System.out.println("查找书籍失败");
            }
        });

        try {
            writeB("over", bw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO 完成查找书籍的函数
     * @param br
     * @param bw
     * @throws IOException
     */
    private static void searchBook(BufferedReader br, BufferedWriter bw) throws IOException {
        List<Book> books = new ArrayList<>();
        // 从缓存读入流中获取书籍名字

        // 在BookMapper中编写合适的sql语句

        // 在数据库中查找

        books.forEach(book -> {
            try {
                writeB("序号:" + book.getId() +
                        ", 书名" + book.getTitle() +
                        ", 简介" + book.getDesc() +
                        ", 价格" + book.getPrice(), bw);

            } catch (IOException e) {
                System.out.println("查找书籍失败");
            }
        });
        writeB("over", bw);
    }

    /**
     * TODO 仿照下面归还书籍的函数完成这个函数
     * @param br
     * @param bw
     * @throws IOException
     */
    private static void addBorrow(BufferedReader br, BufferedWriter bw) throws IOException {
        // 从缓存读入流中获取学生id和书籍id 形式 student_id&book_id

        // 在bookMapper中编写合适的sql语句

        // 向数据库添加数据

        // 添加成功调用writeB("借阅成功",bw)

        // 添加失败调用writeB("借阅失败",bw)
    }

    private static void returnBook(BufferedWriter bw, BufferedReader br) throws IOException {
        String info = br.readLine();
        String student_id = info.split("&")[0];
        String book_id = info.split("&")[1];

        BookMapper bookMapper = MybatisUtil.getBookMapper();
        int i = bookMapper.returnBook(Integer.parseInt(student_id), Integer.parseInt(book_id));
        if (i > 0) {
            try {
                writeB("归还成功", bw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                writeB("归还失败", bw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
