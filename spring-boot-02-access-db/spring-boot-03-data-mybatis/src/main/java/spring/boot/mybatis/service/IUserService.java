package spring.boot.mybatis.service;

import spring.boot.mybatis.pojo.User;

public interface IUserService {
    User getUser(Long id);

    int addUser(User user);

    int modifyUser(User user);

    int removeUser(Long id);

}
