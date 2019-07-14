package spring.boot.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import spring.boot.jdbc.enums.SexEnum;
import spring.boot.jdbc.pojo.User;
import spring.boot.jdbc.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM t_user T WHERE T.ID = ?", new Object[]{id}, this.getRowMapper());
    }

    @Override
    public List<User> findUserList(String userName, String note) {
        String sql = "SELECT * FROM t_user T WHERE T.USER_NAME LIKE CONCAT('%',?,'%') " +
                " AND T.NOTE LIKE CONCAT('%',?,'%')";
        return jdbcTemplate.query(sql, new Object[]{userName, note}, this.getRowMapper());
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO t_user (USER_NAME,SEX,NOTE) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote());
    }

    @Override
    public int modifyUser(User user) {
        String sql = "UPDATE t_user t SET t.USER_NAME = ?,t.SEX=?,t.NOTE = ? WHERE t.ID = ?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote(), user.getId());
    }

    @Override
    public int removeUser(Long id) {
        String sql = "DELETE  FROM t_user WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int queryUserCount() {
        String sql = "SELECT COUNT(*) FROM t_user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private RowMapper<User> getRowMapper() {
        return (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("user_name"));
            int sexId = resultSet.getInt("sex");
            SexEnum sexEnum = SexEnum.getEnumById(sexId);
            user.setSex(sexEnum);
            user.setNote(resultSet.getString("note"));
            return user;
        };
    }
}
