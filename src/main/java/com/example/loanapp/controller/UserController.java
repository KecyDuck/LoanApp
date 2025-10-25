package com.example.loanapp.controller;

import com.example.loanapp.dto.user.UserDataDTO;
import com.example.loanapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    //kedua response di bawah akan mengambil response default nya apabila mau lebih baik
    // dan readable bisa menggunakan custommessage sebagai response seperti "User Created Successfully"

    @GetMapping
    public Page<UserDataDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userService.getAllUsers(PageRequest.of(page, size));
    }

    @PostMapping
    public UserDataDTO createUser (@RequestBody UserDataDTO request) {
        return userService.createUser(request);
    }
}
