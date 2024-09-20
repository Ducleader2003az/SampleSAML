package com.kokobato.huynhduc.kokobatodemo;

import com.kokobato.huynhduc.kokobatodemo.component.JwtTokenUtil;
import com.kokobato.huynhduc.kokobatodemo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home(Authentication authentication) {
        // Xử lý khi người dùng đã được xác thực
        if (authentication != null) {
            return ResponseEntity.ok("Welcome to the home page, " + authentication.getName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authenticated");
        }
    }

    @PostMapping("/home")
    public ResponseEntity<String> homeRespone(Authentication authentication) {
        // Xử lý khi người dùng đã được xác thực
        if (authentication != null) {
            return ResponseEntity.ok("Welcome to the home page, " + authentication.getName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authenticated");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // Spring Security sẽ tự động xử lý SAML2 login, không cần phải xử lý trong controller này
        return ResponseEntity.ok("Redirecting to login...");
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfile(Authentication authentication) {
        // Trả về thông tin người dùng hiện tại đã được xác thực
        if (authentication != null) {
            return ResponseEntity.ok(authentication.getPrincipal());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
    }
}
