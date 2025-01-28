package com.school.Springshigh.controller;

import com.school.Springshigh.entity.Student;
import com.school.Springshigh.model.Credentials;
import com.school.Springshigh.model.ResetPasswordRequest;
import com.school.Springshigh.model.TwoFactorAuth;
import com.school.Springshigh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Student student) {
        return authService.register(student);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        return authService.login(credentials);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody TwoFactorAuth twoFactorAuth) {
        return authService.verifyCode(twoFactorAuth);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return authService.resetPassword(request);
    }

}
