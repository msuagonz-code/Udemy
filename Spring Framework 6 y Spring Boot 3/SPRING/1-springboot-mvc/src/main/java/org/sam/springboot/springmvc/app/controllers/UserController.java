package org.sam.springboot.springmvc.app.controllers;

import jakarta.validation.Valid;
import org.sam.springboot.springmvc.app.entities.User;
import org.sam.springboot.springmvc.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@SessionAttributes({"user"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/view", "/another"})
    public String view(Model model){
        model.addAttribute("title", "Hola mundo Spring Boot!");
        model.addAttribute("message", "Esta es una aplicación de ejemplo usando Spring Boot!");
        model.addAttribute("user", new User("Manuel", "Suárez"));
        return "view";
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("title", "Listado de usuarios");
        model.addAttribute("users", userService.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Formulario usuario");
        return "form";
    }

    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id,  Model model, RedirectAttributes redirect){
        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("title", "Formulario usuario");
            return "form";
        }else{
            redirect.addFlashAttribute("error", "Error el usuario con el id "+ id +" no existe en el sistema");
            return "redirect:/users/";
        }
    }

    @PostMapping
    public String form(@Valid User user, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status){

        if(result.hasErrors()){
            model.addAttribute("title", "Validando Formulario");
            return "form";
        }
        String message = (user.getId() != null && user.getId() > 0) ? "El usuario "+
                user.getName() +
                " se ha actualizado con éxito" : "El usuario "+
                user.getName() +
                " se ha creado con éxito";

        userService.save(user);
        status.setComplete();
        redirect.addFlashAttribute("success", message);
        return "redirect:/users/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isPresent()){
            redirect.addFlashAttribute("success", "El usuario "+
                    optionalUser.get().getName() +
                    " se ha eliminado con éxito");
            userService.remove(id);
            return "redirect:/users/";
        }else{
            redirect.addFlashAttribute("error", "Error el usuario con el id "+ id +" no existe en el sistema");
            return "redirect:/users/";
        }
    }
}
