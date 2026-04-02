package com.finance.finance.dashboard.system.controller;

import com.finance.finance.dashboard.system.dto.LoginDTO;
import com.finance.finance.dashboard.system.dto.UserDTO;
import com.finance.finance.dashboard.system.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        System.out.println("LOGIN API HIT"); // ✅ debug log
        return service.login(dto);
    }
}