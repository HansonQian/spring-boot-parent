package spring.boot.tx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.tx.TxApplication;
import spring.boot.tx.bean.User;
import spring.boot.tx.bean.enums.SexEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {TxApplication.class})
@RunWith(SpringRunner.class)
public class IUserBatchServiceTest {

    @Autowired
    private IUserBatchService batchService;

    @Test
    public void batchSave() throws Exception {
        User user1 = new User();
        user1.setNote("Monday");
        user1.setSex(SexEnum.FEMALE);
        user1.setUserName("星期一");

        User user2 = new User();
        user2.setNote("Tuesday");
        user2.setSex(SexEnum.FEMALE);
        user2.setUserName("星期二");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        int count = batchService.batchSave(userList);

    }

}