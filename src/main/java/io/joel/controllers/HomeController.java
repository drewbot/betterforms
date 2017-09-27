package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private SecretRepository secretRepo;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("secret", new Secret());
        model.addAttribute("mySecrets", secretRepo.findAll());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@ModelAttribute Secret secret) {
        System.out.println(secret);
        secretRepo.save(secret);
        return "redirect:/";
    }

}
