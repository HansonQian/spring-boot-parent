package spring.boot.tx.service;

import spring.boot.tx.bean.User;

import java.util.List;

public interface IUserBatchService {
    int batchSave(List<User> userList);
}
