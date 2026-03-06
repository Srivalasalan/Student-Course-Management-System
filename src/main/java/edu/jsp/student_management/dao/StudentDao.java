package edu.jsp.student_management.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import edu.jsp.student_management.entity.Student;
import edu.jsp.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class StudentDao {
	
	private final StudentRepository repo;

	public Student saveData(Student student) {
		return repo.save(student);
	}

	public Student findById(Long id) {
		Optional<Student> stud=repo.findById(id);
		return stud.orElse(null);
	}

	public Page<Student> findAll(Pageable pageable) {
	    return repo.findAll(pageable); 
	}

	public boolean deleteById(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		else return false;
	}

}
