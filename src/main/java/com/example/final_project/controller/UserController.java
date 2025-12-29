package com.example.final_project.controller;

import com.example.final_project.Entity.User;
import com.example.final_project.Service.ItemService;
import com.example.final_project.Service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final MyUserService myUserService;
    private final ItemService itemService;

    @GetMapping
    public String GetUser() {
        return "User authorized";
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        myUserService.registr(user);
    }

    @GetMapping("items")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getItems() {
        return ResponseEntity.ok(itemService.getAll());
    }
}

