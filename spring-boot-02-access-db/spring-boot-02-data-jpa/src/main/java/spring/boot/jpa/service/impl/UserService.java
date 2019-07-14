package spring.boot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.jpa.dao.UserRepository;
import spring.boot.jpa.entity.User;
import spring.boot.jpa.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }
}
