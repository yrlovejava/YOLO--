package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor//这些注解都是Lombok的，必须要引入lombok后才能使用，当然我已经导入了
public class Book implements Serializable {
    //实现Serializable是为了让对象能够序列化
    //我们需要把对象的状态信息通过网络进行传输，或者需要将对象的状态信息持久化，以便将来使用时都需要把对象进行序列化,因为模拟了CS架构
    @Serial
    private static final long serialVersionUID = -5579372610773209725L;

    private int id;
    private String title;
    private String desc;
    private double price;

    public Book(String title, String desc, double price) {
        this.title = title;
        this.desc = desc;
        this.price = price;
    }
}
