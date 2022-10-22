package com.divizia.dbconstructor.controller;

import com.divizia.dbconstructor.model.entity.User;
import com.divizia.dbconstructor.model.enums.Role;
import com.divizia.dbconstructor.model.service.CustomTableService;
import com.divizia.dbconstructor.model.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping("tables")
public class CustomTableControllerFree {

    private final CustomTableService customTableService;
    private final UserService userService;

    public CustomTableControllerFree(CustomTableService customTableService, UserService userService) {
        this.customTableService = customTableService;
        this.userService = userService;
    }

    @GetMapping("all")
    public String getAll(Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userService.findById(currentUsername);
        User user = optionalUser.orElse(new User());

        model.addAttribute("isAdmin", user.getRole() == Role.ADMIN);
        model.addAttribute("tables", customTableService.findAll());
        return "tables/all";
    }

}
