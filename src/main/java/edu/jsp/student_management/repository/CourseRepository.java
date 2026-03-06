package edu.jsp.student_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.student_management.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	

}
