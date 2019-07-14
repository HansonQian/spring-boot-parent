package spring.boot.tx.service;

import spring.boot.tx.bean.User;

import java.util.List;

public interface IUserService {
    User getUser(Long id);

    int addUser(User user);

    int batchSave(List<User> userList);

    int modifyUser(User user);

    int removeUser(Long id);
}
