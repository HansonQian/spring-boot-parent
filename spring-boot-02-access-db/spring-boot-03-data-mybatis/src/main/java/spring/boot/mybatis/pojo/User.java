package spring.boot.mybatis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.boot.mybatis.pojo.enums.SexEnum;

@Setter
@Getter
@ToString
public class User {
    private Long id;
    private String userName;
    private String note;
    private SexEnum sex;
}
