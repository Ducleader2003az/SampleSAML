package com.kokobato.huynhduc.kokobatodemo.controller;

import com.kokobato.huynhduc.kokobatodemo.dtos.LoginRespone;
import com.kokobato.huynhduc.kokobatodemo.dtos.UserDTO;
import com.kokobato.huynhduc.kokobatodemo.dtos.UserLoginDTO;
import com.kokobato.huynhduc.kokobatodemo.models.Token;
import com.kokobato.huynhduc.kokobatodemo.models.User;
import com.kokobato.huynhduc.kokobatodemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> erros = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(erros);
            }

            User user = userService.register(userDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            Token token = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());

            return ResponseEntity.ok().body(
                    LoginRespone.builder()
                            .message("Login Successfully")
                            .username(token.getUser().getUsername())
                            .roles(token.getUser().getAuthorities().stream().map(a -> a.getAuthority()).toList())
                            .refreshToken(token.getRefresh_token())
                            .token(token.getToken())
                            .userId(token.getUser().getId())
                            .build()

            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
