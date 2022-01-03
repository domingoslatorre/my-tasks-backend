package com.example.mytasksbackend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static List<User> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserReq userReq) {
        User user = new User(userReq.getName(), userReq.getEmail(), userReq.getPassword());
        users.add(user);
        return ResponseEntity.ok(user);
    }

}
