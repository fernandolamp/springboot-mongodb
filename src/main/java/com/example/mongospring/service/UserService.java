package com.example.mongospring.service;

import com.example.mongospring.domain.User;
import com.example.mongospring.dto.UserDTO;
import com.example.mongospring.repository.UserRepository;
import com.example.mongospring.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public  User findById(String id){
        User user = repository.findById(id).orElse(null);
        if (user == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public User fromDto(UserDTO obj){
        return new User(obj.getId(),obj.getName(),obj.getEmail());
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }


    public void delete(User obj){
        repository.delete(obj);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(obj,newObj);
        return repository.save(newObj);
    }

    private void updateData(User obj, User newObj) {
        newObj.setEmail(obj.getEmail());
        newObj.setName(obj.getName());
    }

}
