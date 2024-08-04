package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("select * from book")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "desc", property = "desc"),
            @Result(column = "price", property = "price"),
    })
    List<Book> selectAllBook();


    @Delete("delete from borrow where student_id = #{student_id} and book_id = #{book_id}")
    int returnBook(@Param("student_id") int student_id,@Param("book_id") int book_id);
}

