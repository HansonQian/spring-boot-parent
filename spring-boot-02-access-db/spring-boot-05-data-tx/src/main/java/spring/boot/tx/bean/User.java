package spring.boot.tx.bean;

import lombok.*;
import spring.boot.tx.bean.enums.SexEnum;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String note;
    private SexEnum sex;


    public User(Long id) {
        this.id = id;
    }


}
