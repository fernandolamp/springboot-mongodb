package com.example.mongospring.config;

import com.example.mongospring.domain.User;
import com.example.mongospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria", "Maria@gmail.com");
        User alex = new User(null, "Alex", "Alex@gmail.com");
        User bob = new User(null, "Bob", "Bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));


    }
}