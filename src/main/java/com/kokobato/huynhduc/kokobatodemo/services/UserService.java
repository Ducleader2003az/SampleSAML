package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.dtos.UserDTO;
import com.kokobato.huynhduc.kokobatodemo.exceptions.DataNotFoundException;
import com.kokobato.huynhduc.kokobatodemo.models.Role;
import com.kokobato.huynhduc.kokobatodemo.models.Token;
import com.kokobato.huynhduc.kokobatodemo.models.User;
import com.kokobato.huynhduc.kokobatodemo.repositories.RoleRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.TokenRepository;
import com.kokobato.huynhduc.kokobatodemo.repositories.UserRepository;
import com.kokobato.huynhduc.kokobatodemo.component.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TokenRepository tokenRepository;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .build();

        String passwordEncode = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(passwordEncode);

        Optional<Role> role = roleRepository.findById(userDTO.getRoleId());

        role.orElseThrow(() -> new DataNotFoundException("Role not found!"));

        user.setRole(role.get());

        return userRepository.save(user);
    }

    public Token login(String username, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (username.isEmpty()) {
            throw new DataNotFoundException("Username not found");
        }

        User existingUser = userOptional.get();

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong username or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password,
                existingUser.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        String token = jwtTokenUtil.generateToken(userOptional.get());

        Token jwtToken = tokenService.addToken(existingUser, token);

        return jwtToken;
    }
}
