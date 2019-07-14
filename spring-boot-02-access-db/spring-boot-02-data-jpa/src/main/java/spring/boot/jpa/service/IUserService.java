package spring.boot.jpa.service;

import spring.boot.jpa.entity.User;

public interface IUserService {
    User getUser(Long id);
}
