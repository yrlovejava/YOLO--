package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Borrow {
    private int id;
    private Student student;
    Book book;
}
