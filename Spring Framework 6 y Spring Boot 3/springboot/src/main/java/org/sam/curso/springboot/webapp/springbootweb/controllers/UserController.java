
package org.sam.curso.springboot.webapp.springbootweb.controllers;

import java.util.Arrays;
import java.util.List;

import org.sam.curso.springboot.webapp.springbootweb.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    /* Usando Model */
    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Manuel", "Suárez");
        user.setEmail("manuel@correo.com");
        model.addAttribute("title", "Hola mundo SpringBoot");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model){

        //model.addAttribute("users", users);
        model.addAttribute("title", "Listado de usuarios!");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel(){
        return Arrays.asList(
                    new User("Pepa", "Gonzalez"),
                    new User("Lalo", "Perez", "lalo@correo.com"),
                    new User("Juanita", "Roe", "juana@correo.com"),
                    new User("Andres", "Doe"));
    }
}
