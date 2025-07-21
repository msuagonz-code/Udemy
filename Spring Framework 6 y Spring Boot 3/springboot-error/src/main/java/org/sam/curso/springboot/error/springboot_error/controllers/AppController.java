package org.sam.curso.springboot.error.springboot_error.controllers;


import org.sam.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import org.sam.curso.springboot.error.springboot_error.models.domain.User;
import org.sam.curso.springboot.error.springboot_error.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index(){
        //int value = 100 / 0;
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "OK 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id ){

        User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error, el usuario no existe"));
        return user;

        //Optional<User> optionalUser = service.findById(id);
        //if(optionalUser.isEmpty()){
        //    return ResponseEntity.notFound().build();
        //}
        //return ResponseEntity.ok(optionalUser.orElseThrow());

        //System.out.println(user.getLasname());

    }

}
