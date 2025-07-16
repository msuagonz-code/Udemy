
package org.sam.curso.springboot.webapp.springbootweb.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sam.curso.springboot.webapp.springbootweb.models.User;
import org.sam.curso.springboot.webapp.springbootweb.models.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    /* Usando Model */
    @GetMapping("/details")
    public UserDto details(){

        User user = new User("Manuel", "Suárez");
        
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola mundo SpringBoot");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list(){
        User user = new User("Manuel", "Suárez");
        User user2 = new User("Andres", "Guzman");
        User user3 = new User("Pepe", "Doe");

        List<User> users = Arrays.asList(user, user2, user3);
        //List<User> users = new ArrayList<>();
        //users.add(user);
        //users.add(user2);
        //users.add(user3);

        return users;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap(){

        User user = new User("Manuel", "Suárez");

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo SpringBoot");
        body.put("user", user);

        return body;
    }

}
