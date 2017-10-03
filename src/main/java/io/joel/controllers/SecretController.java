package io.joel.controllers;

import io.joel.models.Secret;
import io.joel.models.User;
import io.joel.repositories.SecretRepository;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class SecretController {

    @Autowired
    private SecretRepository secretRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/secret/{id}")
    public String secretDetail(Model model,
                               @PathVariable("id") Long id) {
        Secret mySecret = secretRepo.findOne(id);
        model.addAttribute("secret", mySecret);
        return "secret";
    }

    @RequestMapping(value = "/secret/{id}", method = RequestMethod.POST)
    public String secretDetailForm(@ModelAttribute Secret secret,
                                   Principal principal) {
        User me = userRepo.findByUsername(principal.getName());
        secret.setOwner(me);
        secretRepo.save(secret);
        return "redirect:/";
    }

    // Delete confirmation page
    @RequestMapping(value = "/secret/{id}/delete")
    public String secretDeleteConfirm(Model model,
                                      @PathVariable("id") Long id) {
        Secret secret = secretRepo.findOne(id);
        model.addAttribute("secret", secret);
        return "secretDeleteConfirm";
    }

    @RequestMapping(value = "/secret/{id}/delete", method = RequestMethod.POST)
    public String secretDelete(@PathVariable("id") Long id) {
        secretRepo.delete(id);
        return "redirect:/";
    }

}
