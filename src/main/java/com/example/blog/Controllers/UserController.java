package com.example.blog.Controllers;

import com.example.blog.DTO.UserDTO.LoginDTO;
import com.example.blog.DTO.UserDTO.UserCreateDTO;
import com.example.blog.DTO.UserDTO.UserResponseDTO;
import com.example.blog.Services.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@Valid @RequestBody UserCreateDTO createDTO) {
        return userService.createUser(createDTO);
    }
    @GetMapping("/{uuid}")
    public UserResponseDTO getUser(@PathVariable String uuid){
        return userService.getUser(uuid);
    }
    @PutMapping("/{uuid}")
    public UserResponseDTO updateUser(@PathVariable String uuid, UserCreateDTO dto){
        return userService.updateUser(uuid, dto);
    }

}
