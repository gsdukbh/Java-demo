package top.werls.springbootvalidfromdemo;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author leejiawei
 */
public class Book {

    @NotNull
    @Max(127)
    @Min(1)
    private int id;
    @NotNull(message = "不能为空")
    private String  name;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
