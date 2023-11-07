package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long id);

    boolean deleteById(Long id);

    List<User> findAll();

    boolean hasUser(Long id);
}
