package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.models.User;
import io.joel.repositories.SecretRepository;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private SecretRepository secretRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/")
    public String index(Model model,
                        Principal principal) {
        if (principal != null) {
            User me = userRepo.findByUsername(principal.getName());
            model.addAttribute("mySecrets", secretRepo.findAllByOwner(me));
        }
        model.addAttribute("secret", new Secret());
        return "index";
    }

    @RequestMapping(value = "/secret", method = RequestMethod.POST)
    public String index(@ModelAttribute Secret secret,
                        Principal principal) {
        User me = userRepo.findByUsername(principal.getName());
        secret.setOwner(me);
        secretRepo.save(secret);
        return "redirect:/";
    }

}
