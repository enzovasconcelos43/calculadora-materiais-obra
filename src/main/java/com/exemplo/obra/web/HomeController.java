package com.exemplo.obra.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redireciona a raiz "/" para a página inicial do front-end (index.xhtml),
 * para que o usuário não precise digitar o nome do arquivo na URL.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.xhtml";
    }
}
