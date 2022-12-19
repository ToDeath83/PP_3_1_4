package ru.kata.spring.boot_security.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void readUser() {
        UserService userService = new UserServiceImp(userDao, passwordEncoder);

        User user = userService.readUser(2L);
//        User user = new User();
//        user.setName("Admin");

        Assertions.assertEquals(user.getName(), "Sergey");
    }
}