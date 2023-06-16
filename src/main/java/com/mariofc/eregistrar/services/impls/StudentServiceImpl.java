package com.mariofc.eregistrar.services.impls;

import com.mariofc.eregistrar.models.Student;
import com.mariofc.eregistrar.repositories.StudentRepository;
import com.mariofc.eregistrar.services.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @Override
    public Optional<Student> get(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> search(String param) {
        return studentRepository.findAllByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrMiddleNameContainingOrLastNameContainingOrIsInternationalContainingIgnoreCase
                (param, param, param, param,param);
    }
}
