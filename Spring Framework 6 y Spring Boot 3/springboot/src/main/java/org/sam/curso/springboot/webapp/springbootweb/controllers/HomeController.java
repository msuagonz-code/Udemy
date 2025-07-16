package org.sam.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "/home"})
    public String home(){

        /* Redirije reiniciando la peticion HTTP
         * (Cambia la URL)
         */
        //return "redirect:/details";
        
        /* Redirije dentro de la misma peticion HTTP, sin perder parametros de la peticion
         * (No cambia la URL)
         */
        return "forward:/details";
    }

}
