package spring.boot.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.tx.bean.User;
import spring.boot.tx.service.IUserBatchService;
import spring.boot.tx.service.IUserService;

import java.util.List;

@Service
public class UserBatchService implements IUserBatchService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public int batchSave(List<User> userList) {
        int count = 0;
        for (User user : userList) {
            //调用资方法,将使用@Transactional定义的事务传播行为
            count += userService.addUser(user);
        }
        return count;
    }
}
