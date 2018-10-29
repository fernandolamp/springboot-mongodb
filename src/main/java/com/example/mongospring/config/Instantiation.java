package com.example.mongospring.config;

import com.example.mongospring.domain.Post;
import com.example.mongospring.domain.User;
import com.example.mongospring.dto.AuthorDTO;
import com.example.mongospring.dto.CommentDTO;
import com.example.mongospring.repository.PostRepository;
import com.example.mongospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria", "Maria@gmail.com");
        User alex = new User(null, "Alex", "Alex@gmail.com");
        User bob = new User(null, "Bob", "Bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null,"Partiu viagem",sdf.parse("21/03/2018"),"vou para passagarda",new AuthorDTO(maria));
        Post post2 = new Post(null,"Bom dia!",sdf.parse("23/05/2018"),"Acordei feliz!",new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO("Boa viagem",sdf.parse("21/03/2018"),new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite",sdf.parse("22/03/2018"),new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia",sdf.parse("24/03/2018"),new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1,comment2,comment3));


        postRepository.saveAll(Arrays.asList(post1,post2));



        maria.setPosts(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
