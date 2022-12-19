package ru.kata.spring.boot_security.demo.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void getUsers() {
        User user = userRepository.findByUsername("ToDeath");

        Assertions.assertEquals(user.getName(), "Sergey");
    }

    @Test
    void saveUser() {
    }

    @Test
    void readUser() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void listRoles() {
    }
}