package spring.boot.jpa.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.jpa.JpaApplication;
import spring.boot.jpa.entity.User;
import spring.boot.jpa.service.IUserService;

import static org.junit.Assert.*;

@SpringBootTest(classes = {JpaApplication.class})
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void getUser() throws Exception {
        User user = userService.getUser(1L);
        System.out.println(user);
    }

}