package com.example.mongospring.resources;

import com.example.mongospring.domain.Post;
import com.example.mongospring.domain.User;
import com.example.mongospring.dto.UserDTO;
import com.example.mongospring.resources.util.URL;
import com.example.mongospring.service.PostService;
import com.example.mongospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }
    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text ){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/textsearch")
    public ResponseEntity<List<Post>> fullsearch(@RequestParam(value = "text",defaultValue = "") String text,
                                                 @RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate",defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text,min,max);
        return ResponseEntity.ok().body(list);

    }
}
