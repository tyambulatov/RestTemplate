package org.example;

import org.example.config.Config;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Config config = new Config();
        UserRepository<User, Long> userRepository = config.getUserRepository();
        UserService userService = new UserServiceImpl(userRepository);

        User user = new User();
        user.setName("User1");
        userRepository.save(user);

        System.out.println(userService.findAll());

        System.out.println(userService.findById(11L));
        System.out.println(userService.deleteById(2L));
        System.out.println(userService.findAll());
        System.out.println();
    }
}