package com.camel.wms.controller;

import com.camel.wms.model.Role;
import com.camel.wms.model.User;
import com.camel.wms.repository.UserRepository;
import com.camel.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("existedUsername", userFromDb);
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/checkman/create")
    public String checkmanCreateForm() {
        return "checkmanCreate";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/checkman/create")
    public String checkmanCreate(User user, RedirectAttributes redirectAttrs) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            redirectAttrs.addFlashAttribute("error", "Пользователь существует");
            return "redirect:/checkman/create";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.CHECKMAN));
        userRepository.save(user);
        return "redirect:/getUsers";
    }
}
