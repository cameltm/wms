package com.camel.wms.controller;

import com.bsuir.WarehouseManagementSystem.model.Role;
import com.bsuir.WarehouseManagementSystem.model.User;
import com.bsuir.WarehouseManagementSystem.repository.UserRepository;
import com.bsuir.WarehouseManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
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
    public String checkmanCreateForm(){
        return "checkmanCreate";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/checkman/create")
    public String checkmanCreate(User user, RedirectAttributes redirectAttrs){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            redirectAttrs.addFlashAttribute("error", "Пользователь существует");
            return "redirect:/checkman/create";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.CHECKMAN));
        userRepository.save(user);
        return "redirect:/getUsers";
    }
}
