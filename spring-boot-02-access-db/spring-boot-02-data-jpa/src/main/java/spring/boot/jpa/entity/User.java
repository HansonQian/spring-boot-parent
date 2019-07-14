package spring.boot.jpa.entity;

import org.hibernate.annotations.Proxy;
import spring.boot.jpa.entity.converters.SexConverter;
import spring.boot.jpa.entity.enums.SexEnum;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@Proxy(lazy = false)//解决LazyInitializationException问题
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = SexConverter.class)
    @Column(name = "sex")
    private SexEnum sexEnum;

    @Column(name = "user_name", length = 60)
    private String userName;

    @Column
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sexEnum=" + sexEnum +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
