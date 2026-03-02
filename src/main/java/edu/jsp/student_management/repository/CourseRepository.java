package edu.jsp.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.jsp.student_management.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Query("SELECT c FROM Course c LEFT JOIN FETCH c.students")
	List<Course> findAllWithStudents();

}
