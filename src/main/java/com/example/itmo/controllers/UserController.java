package com.example.itmo.controllers;

import com.example.itmo.model.dto.request.UserInfoRequest;
import com.example.itmo.model.dto.response.UserInfoResponse;
import com.example.itmo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserInfoResponse createUser(@RequestBody UserInfoRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/all")
    public Page<UserInfoResponse> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer perPage,
                                              @RequestParam(defaultValue = "firstName") String sort,
                                              @RequestParam(defaultValue = "ASC") Sort.Direction order) {
        return userService.getAllUsers(page, perPage, sort, order);
    }

}

