package edu.jsp.student_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.student_management.dto.StudentResponseDTO;
import edu.jsp.student_management.entity.Student;
import edu.jsp.student_management.service.StudentService;
import edu.jsp.student_management.util.ResponseStructure;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
	
	private final StudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Student>> save(@Valid @RequestBody Student student){
		return service.saveData(student);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> findById(@PathVariable Long id){
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Student>>> findAll(){
		return service.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Long id){
		return service.deleteById(id);
	}
	
	@PostMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<ResponseStructure<Student>> assignCourse(
	        @PathVariable Long studentId,
	        @PathVariable Long courseId) {

	    return service.assignCourse(studentId, courseId);
	}

}
