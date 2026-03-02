package edu.jsp.student_management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.jsp.student_management.entity.Course;
import edu.jsp.student_management.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseDao {

	private final CourseRepository repo;

	public Course saveData(Course c) {
		return repo.save(c);
	}

	public Course findById(Long id) {
		Optional<Course> c=repo.findById(id);
		return c.orElse(null);
	}

	public List<Course> findAllData() {
		return repo.findAllWithStudents();
	}

	public boolean deleteById(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
