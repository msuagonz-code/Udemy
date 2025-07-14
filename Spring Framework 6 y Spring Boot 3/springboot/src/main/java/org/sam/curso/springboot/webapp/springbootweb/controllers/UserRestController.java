
package org.sam.curso.springboot.webapp.springbootweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.sam.curso.springboot.webapp.springbootweb.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    /* Usando Model */
    @GetMapping("/details")
    public Map<String, Object> details(){

        User user = new User("Manuel", "Suárez");

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo SpringBoot");
        body.put("user", user);

        return body;
    }

}
