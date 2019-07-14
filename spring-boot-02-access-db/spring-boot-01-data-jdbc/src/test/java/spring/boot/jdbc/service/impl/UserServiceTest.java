package spring.boot.jdbc.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.jdbc.JdbcApplication;
import spring.boot.jdbc.enums.SexEnum;
import spring.boot.jdbc.pojo.User;
import spring.boot.jdbc.service.IUserService;

import java.util.List;

@SpringBootTest(classes = {JdbcApplication.class})
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void getUser() throws Exception {
        User user = userService.getUser(1L);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("未找到数据");
        }
    }

    @Test
    public void findUserList() throws Exception {
        List<User> userList = userService.findUserList("zhang", "科研北路");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUserName("zhangsan");
        user.setNote("科研北路");
        user.setSex(SexEnum.MALE);
        User user2 = new User();
        user2.setUserName("lisi");
        user2.setNote("科研北路");
        user2.setSex(SexEnum.FEMALE);
        User user3 = new User();
        user3.setUserName("zhangliu");
        user3.setNote("科研北路");
        user3.setSex(SexEnum.FEMALE);
        userService.addUser(user);
        userService.addUser(user2);
        userService.addUser(user3);
    }

    @Test
    public void modifyUser() throws Exception {
        User user = new User();
        user.setUserName("zhangwuji");
        user.setNote("科研西路");
        user.setSex(SexEnum.MALE);
        user.setId(1L);
        userService.modifyUser(user);
    }

    @Test
    public void removeUser() throws Exception {
        userService.removeUser(4L);
    }

    @Test
    public void queryUserCount() throws Exception {
        System.out.println(userService.queryUserCount());
    }

}