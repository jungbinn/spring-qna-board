package com.line2019.qna.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @GetMapping("loginForm")
    public String loginForm() {
        return "users/login";
    }

    @PostMapping("login")
    public String login(String email, String password, HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (!user.getPassword().equals(password)) {
            System.out.printf("Login Fail for user %s: password %s != %s\n", user.getEmail(), user.getPassword(), password);
            return "redirect:/users/loginForm";
        }

        session.setAttribute(HttpSessionUtils.SESSION_KEY, user);
        System.out.printf("Login Success %s", user.getEmail());
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    @GetMapping("{uid}/updateForm")
    public String updateForm(@PathVariable Long uid, Model model, HttpSession session) {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User user = userRepository.findById(uid).get();
        model.addAttribute(user);
        return "users/update";
    }

    @PutMapping("{uid}/update")
    public String update(@PathVariable Long uid, String email, String name, String password, HttpSession session) {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User user = userRepository.findById(uid).get();
        user.update(email, name, password);
        userRepository.save(user);
        session.setAttribute(HttpSessionUtils.SESSION_KEY, user);
        return "redirect:/users";
    }
}
