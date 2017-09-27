package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecretController {

    @Autowired
    private SecretRepository secretRepo;

    @RequestMapping(value = "/secret/{id}")
    public String secretDetail(Model model,
                               @PathVariable("id") Long id) {
        Secret mySecret = secretRepo.findOne(id);
        model.addAttribute("secret", mySecret);
        return "secret";
    }
}
