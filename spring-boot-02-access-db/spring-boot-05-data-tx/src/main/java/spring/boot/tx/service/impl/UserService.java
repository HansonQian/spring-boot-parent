package spring.boot.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.tx.bean.User;
import spring.boot.tx.dao.UserMapper;
import spring.boot.tx.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public User getUser(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            //propagation = Propagation.REQUIRED,
            //propagation = Propagation.REQUIRES_NEW,
            propagation = Propagation.NESTED,
            timeout = 1)
    public int addUser(User user) {
        return userMapper.add(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public int batchSave(List<User> userList) {
        //如果在同一个类内部通过直接调用方法名来调用当前类的其他方法,事务的传播行为是不生效的
        //原因：AOP不给给代理对象代理两次
        //解决办法(1):可以创建另外一个类来调用
        //解决办法(2):通过从IOC容器中获取本类对象,在进行调用
        //IUserService userService = applicationContext.getBean(IUserService.class);
        return 0;
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
