package com.jaldimall.controller;


import com.jaldimall.model.User;
import com.jaldimall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        return ResponseEntity.ok(user);
    }

}
