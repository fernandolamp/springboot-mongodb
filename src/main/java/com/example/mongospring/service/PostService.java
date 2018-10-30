package com.example.mongospring.service;

import com.example.mongospring.domain.Post;
import com.example.mongospring.repository.PostRepository;
import com.example.mongospring.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;


    public Post findById(String id){
        Post post = repository.findById(id).orElse(null);
        if (post == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }

    public List<Post> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
