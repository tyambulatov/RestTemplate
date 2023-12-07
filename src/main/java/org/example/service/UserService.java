package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    /*
   Should save only when there is no such User in a db.
    */
    User save(User user);

    User findById(Long id);

    boolean deleteById(Long id);

    List<User> findAll();

    User update(User user);

}
