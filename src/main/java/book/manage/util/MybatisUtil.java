package book.manage.util;

import book.manage.mapper.BookMapper;
import book.manage.mapper.LoginMapper;
import book.manage.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

//这是mybatis的工具类
public class MybatisUtil {

    //sessionFactory是MyBatis的关键对象,它是个单个数据库映射关系经过编译后的内存镜像.
    private final static SqlSessionFactory sessionFactory;

    static {
        try {
            //通过xml来创建SqlSessionFactory
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StudentMapper getStudentMapper(){
        try(SqlSession session = sessionFactory.openSession(true)){
            return session.getMapper(StudentMapper.class);
        }
    }

    public static LoginMapper getLoginMapper(){
        try(SqlSession session = sessionFactory.openSession(true)){
            return session.getMapper(LoginMapper.class);
        }
    }

    public static BookMapper getBookMapper(){
        try (SqlSession session = sessionFactory.openSession(true)){
            return session.getMapper(BookMapper.class);
        }
    }
}
