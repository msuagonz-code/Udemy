
package org.sam.curso.springboot.webapp.springbootweb.controllers;

import java.util.Map;

import org.sam.curso.springboot.webapp.springbootweb.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    /* Usando Model */
    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Manuel", "Suárez");

        model.addAttribute("title", "Hola mundo SpringBoot");
        model.addAttribute("user", user);

        return "details";
    }

}
