package com.camel.wms.controller;

import com.camel.wms.model.User;
import com.camel.wms.repository.UserRepository;
import com.camel.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getUsers")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{user}/edit")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user/{id}/edit")
    public String editUser(@PathVariable(value = "id") Long id,
                           @ModelAttribute User user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttrs) {
        user.setId(id);
        userService.saveUser(user);

        redirectAttrs.addFlashAttribute("success", "Профиль обновлен");

        return "redirect:/user/{id}/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user/{id}/remove")
    public String removeUser(@PathVariable(value = "id") Long id) {
        userService.removeUser(id);
        return "redirect:/getUsers";
    }

    @GetMapping("/user/update")
    public String getProfile(Model model,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/user/update")
    public String updateProfile(@ModelAttribute User updatedUser,
                                BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        User userFromDb = userRepository.findByUsername(updatedUser.getUsername());
        if (userFromDb != null) {
            redirectAttrs.addFlashAttribute("error", "Пользователь существует");
            return "redirect:/user/update";
        } else {
            redirectAttrs.addFlashAttribute("success", "Профиль обновлен");
            userService.saveUser(updatedUser);
        }

        return "redirect:/user/update";
    }


    @PostMapping("/findUser")
    public String findOrder(Model model, @RequestParam String filter) {

        if (filter.isEmpty()) {
            model.addAttribute("users", userService.findAll());
        } else {
            User user = userService.getUserByUsername(filter);
            model.addAttribute("users", user);
        }

        return "userList";
    }
}
