package spring.boot.tx.service.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.tx.TxApplication;
import spring.boot.tx.bean.User;
import spring.boot.tx.service.IUserService;

@SpringBootTest(classes = {TxApplication.class})
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void getUser() throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);
    }

    @org.junit.Test
    public void addUser() throws Exception {
    }

    @org.junit.Test
    public void modifyUser() throws Exception {
    }

    @org.junit.Test
    public void removeUser() throws Exception {
    }

}