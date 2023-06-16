package com.mariofc.eregistrar.repositories;

import com.mariofc.eregistrar.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE " +
            "studentNumber LIKE CONCAT('%', :param, '%') " +
            "OR firstName LIKE CONCAT('%', :param, '%') " +
            "OR middleName LIKE CONCAT('%', :param, '%') " +
            "OR lastName LIKE CONCAT('%', :param, '%') " +
            "OR CAST(cgpa AS string) LIKE CONCAT('%', :param, '%') " +
            "OR isInternational LIKE CONCAT('%', :param, '%')")
    List<Student> searchStudents(@Param("param") String param);

    List<Student> findAllByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrMiddleNameContainingOrLastNameContainingOrIsInternationalContainingIgnoreCase
            (String p1, String p2, String p3, String p4, String p6);
}
