package io.joel.controllers;

import io.joel.models.Role;
import io.joel.models.User;
import io.joel.repositories.RoleRepository;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(name = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public String signupForm(@ModelAttribute User user) {
        // Role
        Role userRole = roleRepo.findByName("ROLE_USER");
        // set the password to the encrypted version
        // of what the password already is
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(userRole);
        userRepo.save(user);
        return "redirect:/login";
    }
}
