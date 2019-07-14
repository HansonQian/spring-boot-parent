package spring.boot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.mybatis.dao.UserMapper;
import spring.boot.mybatis.pojo.User;
import spring.boot.mybatis.service.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.add(user);
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public int removeUser(Long id) {
        return userMapper.delUserById(id);
    }
}
