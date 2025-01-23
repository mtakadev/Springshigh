package com.school.Springshigh.controller;

import com.school.Springshigh.entity.Student;
import com.school.Springshigh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
}
