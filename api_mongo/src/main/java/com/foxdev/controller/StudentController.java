package com.foxdev.controller;

import com.foxdev.model.Student;
import com.foxdev.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentsService;
    @GetMapping
    public List<Student> fetchAllStudents(){
        //return studentsService
        return studentsService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable String id){
        //return studentsService
        return studentsService.findStudentById(id);
    }
}
