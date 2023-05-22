package com.foxdev.repository;

import com.foxdev.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    // Metodo mejorado directo
    Optional<Student> findStudentByEmail(String email);

    //@Query("")
    //void test();

}
