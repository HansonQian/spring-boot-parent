package spring.boot.jdbc.service;

import spring.boot.jdbc.pojo.User;

import java.util.List;

public interface IUserService {
    User getUser(Long id);

    List<User> findUserList(String userName, String note);

    int addUser(User user);

    int modifyUser(User user);

    int removeUser(Long id);

    int queryUserCount();
}
