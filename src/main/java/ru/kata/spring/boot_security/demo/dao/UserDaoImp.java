package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserDaoImp(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user, long[] listRoles) {
        Set<Role> rolesSet = new HashSet<>();
        for (long listRole : listRoles) {
            rolesSet.add(roleRepository.findById(listRole));
        }
        user.setRoles(rolesSet);
        userRepository.save(user);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}