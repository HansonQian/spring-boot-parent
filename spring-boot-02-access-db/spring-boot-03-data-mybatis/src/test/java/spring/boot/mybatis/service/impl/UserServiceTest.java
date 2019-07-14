package spring.boot.mybatis.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.mybatis.MyBatisApplication;
import spring.boot.mybatis.pojo.User;
import spring.boot.mybatis.pojo.enums.SexEnum;
import spring.boot.mybatis.service.IUserService;

@SpringBootTest(classes = {MyBatisApplication.class})
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void getUser() throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUserName("DVD");
        user.setSex(SexEnum.MALE);
        user.setNote("是个2B");
        userService.addUser(user);
    }

    @Test
    public void modifyUser() throws Exception {
    }

    @Test
    public void removeUser() throws Exception {
    }

}