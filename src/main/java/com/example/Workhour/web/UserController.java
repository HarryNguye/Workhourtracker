package com.example.Workhour.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Workhour.domain.User;
import com.example.Workhour.domain.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Lista kaikista käyttäjistä
    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    // Näyttää lomakkeen uuden käyttäjän luomiseksi
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    // Tallentaa uuden käyttäjän
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    // Näyttää lomakkeen käyttäjän muokkaamiseksi
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return userOptional.isPresent() ? "user/edit" : "redirect:/user";
    }

    // Päivittää käyttäjän
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") long id, @ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    // Poistaa käyttäjän
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
