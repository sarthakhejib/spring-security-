package com.api.spring_security.controller;

import com.api.spring_security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students= new ArrayList<>(List.of(
            new Student(1, "Sarthak",55),
             new Student(2, "Kiran",90),
             new Student(3, "Harsh",70),
            new Student(4, "Paritosh",80)
    ));
    @GetMapping("/students")
    public List<Student>  getStudents(){
        return students;
    }

    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody Student student){
        students.add(student);
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }
}
