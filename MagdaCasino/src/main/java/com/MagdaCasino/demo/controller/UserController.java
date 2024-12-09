package com.MagdaCasino.demo.controller;

import com.MagdaCasino.demo.model.User;
import com.MagdaCasino.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/play/{id}")
    public String playGame(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("user", user);
        return "game";
    }

    @PostMapping("/play/{id}")
    public String makeGuess(@PathVariable Long id, @RequestParam int guess, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        String result = userService.playGame(user, guess);
        model.addAttribute("user", user);
        model.addAttribute("result", result);
        return "game";
    }

    // Método para eliminar un usuario
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delateById(id);
        return "redirect:/users";
    }

    // Método para editar un usuario (mostrar formulario con datos actuales)
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("user", user);
        return "editUser";
    }

    // Método para actualizar un usuario
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);  // Asegurarse de que el ID se mantenga al actualizar
        userService.save(user);
        return "redirect:/users";
    }
}

