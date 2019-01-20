package com.line2019.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("register")
    public String createForm() {
        return "users/register";
    }

    @PostMapping("")
    public String create(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/";
    }
}
