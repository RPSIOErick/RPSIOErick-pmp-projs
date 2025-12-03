package com.erick.web.controller;

import com.erick.entity.User;
import com.erick.repository.UserRepository;
import com.erick.web.dto.CreateDto;
import com.erick.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody @Valid CreateDto createDto) {
        User savedUser = repository.save(UserMapper.toEntity(createDto));
        return ResponseEntity.ok(savedUser);
    }

}
