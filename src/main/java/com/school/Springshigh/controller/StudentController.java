package com.school.Springshigh.controller;

import com.school.Springshigh.entity.Student;
import com.school.Springshigh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody Student student) {
        student.setEnrollmentDate(LocalDate.now());
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        Optional<Student> student = studentRepository.findByEmail(credentials.get("email"));
        if (student.isPresent() && student.get().getPassword().equals(credentials.get("password"))) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

// TODO: 2FA Implementation
}
