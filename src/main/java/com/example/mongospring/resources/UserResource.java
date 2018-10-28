package com.example.mongospring.resources;

import com.example.mongospring.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User user = new User(1,"teste","cabelo@gmail.com");
        List<User> list = new ArrayList<>();
        list.add(user);
        return ResponseEntity.ok().body(list);
    }
}
