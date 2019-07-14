package spring.boot.tx.dao;

import org.apache.ibatis.annotations.*;
import spring.boot.tx.bean.User;

public interface UserMapper {
    @Select("SELECT * from t_user where id = #{id}")
    User getUserById(Long id);

    @Delete("DELETE FROM t_user where id  = #{id}")
    int delUserById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO t_user (USER_NAME,SEX,NOTE) VALUES (#{userName},#{sex.id},#{note})")
    int add(User user);

    @Update("UPDATE t_user t SET t.USER_NAME = #{userName},t.SEX=#{sex.id},t.NOTE = #{note} WHERE t.ID = #{id}")
    int update(User user);
}
