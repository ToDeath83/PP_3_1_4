package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void saveUser(User user, long[] listRoles);

    User readUser(Long id);

    User findByUsername(String username);

    void deleteUser(User user);

    List<Role> listRoles();
}