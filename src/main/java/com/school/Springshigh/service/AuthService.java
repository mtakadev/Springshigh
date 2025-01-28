package com.school.Springshigh.service;

import com.school.Springshigh.config.CodeGenerator;
import com.school.Springshigh.config.CodeValidator;
import com.school.Springshigh.config.JwtUtil;
import com.school.Springshigh.entity.Student;
import com.school.Springshigh.model.Credentials;
import com.school.Springshigh.model.ResetPasswordRequest;
import com.school.Springshigh.model.TwoFactorAuth;
import com.school.Springshigh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<String> register(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setEnrollmentDate(LocalDate.now());
        studentRepository.save(student);
        return ResponseEntity.ok("Registration successful");
    }

    public ResponseEntity<String> login(Credentials credentials) {
        Optional<Student> student = studentRepository.findByEmail(credentials.getEmail());
        if (student.isPresent() && passwordEncoder.matches(credentials.getPassword(), student.get().getPassword())) {
            if (student.get().getEnrollmentDate().getYear() == 2024) {
                return ResponseEntity.ok("RESET_PASSWORD");
            }
            String code = CodeGenerator.generateCode();
            mailSender.sendCode(student.get().getEmail(), code);
            return ResponseEntity.ok("2FA_CODE_SENT");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    public ResponseEntity<String> verifyCode(TwoFactorAuth twoFactorAuth) {
        if (CodeValidator.validate(twoFactorAuth.getEmail(), twoFactorAuth.getCode())) {
            String token = jwtUtil.generateToken(twoFactorAuth.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid code");
    }

    public ResponseEntity<String> resetPassword(ResetPasswordRequest request) {
        Optional<Student> student = studentRepository.findByEmail(request.getEmail());
        if (student.isPresent()) {
            student.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
            studentRepository.save(student.get());
            return ResponseEntity.ok("Password reset successful");
        }
        return ResponseEntity.status(404).body("Student not found");
    }

}
