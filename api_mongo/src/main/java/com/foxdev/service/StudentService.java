package com.foxdev.service;

import com.foxdev.model.Student;
import com.foxdev.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student findStudentById(String id){
        Optional<Student> response = studentRepository.findById(id);
        return response.get();
    }
}
