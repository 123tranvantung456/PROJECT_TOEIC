package com.javaweb.toeic.api.web;

import com.javaweb.toeic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;
    @GetMapping("/{username}")
    public String getEmail(@PathVariable String username) {
        return userService.getEmail(username);
    }
}
