package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -911230002009353580L;

    private int id;
    private String name;
    private String gender;
    private int grade;
    private String password;

    public Student(String name, String gender, int grade) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }
}
