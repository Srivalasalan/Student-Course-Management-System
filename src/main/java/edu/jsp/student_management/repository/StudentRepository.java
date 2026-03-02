package edu.jsp.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.student_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
