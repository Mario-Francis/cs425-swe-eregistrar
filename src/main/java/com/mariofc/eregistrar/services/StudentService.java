package com.mariofc.eregistrar.services;

import com.mariofc.eregistrar.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student create(Student student);
    Student update(Student student);
    void delete(Integer studentId);
    List<Student> getAll();
    Optional<Student> get(Integer studentId);
    List<Student> search(String param);
}
